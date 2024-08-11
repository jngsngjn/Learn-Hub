package project.homelearn.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import project.homelearn.entity.curriculum.Curriculum;
import project.homelearn.entity.student.Attendance;
import project.homelearn.entity.user.LoginHistory;
import project.homelearn.entity.user.Role;
import project.homelearn.entity.user.User;
import project.homelearn.repository.curriculum.CurriculumRepository;
import project.homelearn.repository.user.AttendanceRepository;
import project.homelearn.repository.user.LoginHistoryRepository;
import project.homelearn.repository.user.UserRepository;
import project.homelearn.service.jwt.CookieService;
import project.homelearn.service.jwt.JwtService;
import project.homelearn.service.common.RedisService;
import project.homelearn.service.student.BadgeService;

import java.io.IOException;
import java.time.*;

import static java.time.DayOfWeek.*;
import static project.homelearn.config.security.JwtConstants.*;
import static project.homelearn.entity.student.AttendanceType.*;
import static project.homelearn.entity.student.badge.BadgeConstants.*;
import static project.homelearn.entity.user.Role.ROLE_STUDENT;

@Slf4j
@RequiredArgsConstructor
public class CustomLoginFilter extends UsernamePasswordAuthenticationFilter {

    private final JwtService jwtService;
    private final CookieService cookieService;
    private final RedisService redisService;
    private final AuthenticationManager authenticationManager;
    private final LoginHistoryRepository loginHistoryRepository;
    private final UserRepository userRepository;
    private final AttendanceRepository attendanceRepository;
    private final BadgeService badgeService;
    private final CurriculumRepository curriculumRepository;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        String username = obtainUsername(request);
        String password = obtainPassword(request);

        log.info("다음 사용자가 로그인 시도 : {}", username);

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password, null);
        return authenticationManager.authenticate(authToken);
    }

    // 로그인 성공 시
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) {
        String username = authentication.getName();
        String role = authentication.getAuthorities().iterator().next().getAuthority();

        if ("ROLE_STUDENT".equals(role)) {
            Curriculum curriculum = curriculumRepository.findCurriculumByUsername(username);
            LocalDate startDate = curriculum.getStartDate();
            LocalDate endDate = curriculum.getEndDate();
            LocalDate currentDate = LocalDate.now();

            if ((currentDate.isBefore(startDate) || currentDate.isAfter(endDate))) {
                response.setStatus(403);
                response.setContentType("text/plain; charset=UTF-8");
                try {
                    response.getWriter().write("교육과정이 시작하지 않았거나 종료되었습니다.");
                } catch (IOException e) {
                    log.error("Error write msg", e);
                    response.setStatus(500);
                }
                return;
            }
        }

        // 토큰 생성
        String access = jwtService.createJwt(ACCESS_TOKEN_HEADER_NAME, username, Role.valueOf(role), ACCESS_TOKEN_EXPIRATION);
        String refresh = jwtService.createJwt(REFRESH_TOKEN_COOKIE_NAME, username, Role.valueOf(role), REFRESH_TOKEN_EXPIRATION);

        // Radis에 refresh 토큰 저장
        redisService.saveRefreshToken(username, refresh, Duration.ofMillis(REFRESH_TOKEN_EXPIRATION));

        /**
         * 응답 설정
         * - Access Token -> Header
         * - Refresh Token -> Cookie
         * - Role -> Header
         */
        response.setHeader(ACCESS_TOKEN_HEADER_NAME, "Bearer " + access);
        response.addCookie(cookieService.createRefreshCookie(REFRESH_TOKEN_COOKIE_NAME, refresh));
        response.setHeader("role", role);
        response.setStatus(HttpStatus.OK.value());

        if ("ROLE_STUDENT".equals(role)) {
            User user = userRepository.findByUsername(username);
            boolean todayLogin = loginHistoryRepository.existsByUserAndLoginDateTimeIsToday(user);
            if (todayLogin) {
                log.info("오늘 이미 로그인 한 사용자 : {}", username);
            } else {
                log.info("오늘 첫 로그인 사용자 : {}", username);

                // 첫 로그인 시 배지 지급
                boolean exists = loginHistoryRepository.existsByUser(user);
                if (!exists) {
                    log.info("발자취 배지 획득 = {}", username);
                    badgeService.getBadge(user, FOOT_PRINT);
                }

                // 로그인 기록 저장
                loginHistoryRepository.save(new LoginHistory(user));
                long count = loginHistoryRepository.countConsecutiveLoginDatesByUser(user);
                log.info("연속 로그인 횟수 = {}", count);

                if (count == 7) {
                    log.info("7일 연속 로그인 배지 획득 = {}", username);
                    badgeService.getBadge(user, LOGIN_7);
                }
                if (count == 30) {
                    log.info("30일 연속 로그인 배지 획득 = {}", username);
                    badgeService.getBadge(user, LOGIN_30);
                }

                DayOfWeek dayOfWeek = LocalDate.now().getDayOfWeek();
                if (!(dayOfWeek == SATURDAY || dayOfWeek == SUNDAY)) {
                    // 출석 체크 진행
                    processStudentAttendance(user);
                }
            }
        }
        log.info("다음 사용자가 로그인 성공 : {}", username);
    }

    private void processStudentAttendance(User user) {
        if (!attendanceRepository.existsByUser(user)) {
            LocalDateTime loginLog = loginHistoryRepository.findUserLoginDateTime(user);

            LocalTime loginTime = loginLog.toLocalTime();
            LocalTime attendanceDeadline = LocalTime.of(9, 40);
            LocalTime lateDeadline = LocalTime.of(14, 0);

            Attendance attendance;
            if (loginTime.isBefore(attendanceDeadline)) { // 출석
                attendance = new Attendance(user, ATTENDANCE, loginLog.toLocalDate());
            } else if (loginTime.isBefore(lateDeadline)) { // 지각
                attendance = new Attendance(user, LATE, loginLog.toLocalDate());
            } else { // 결석
                attendance = new Attendance(user, ABSENT, loginLog.toLocalDate());
            }
            attendanceRepository.save(attendance);
        }
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) {
        log.warn("인증 실패 = {}", failed.getMessage());
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
    }
}