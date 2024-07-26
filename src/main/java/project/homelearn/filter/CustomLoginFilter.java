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
import project.homelearn.entity.student.Attendance;
import project.homelearn.entity.user.LoginHistory;
import project.homelearn.entity.user.Role;
import project.homelearn.entity.user.User;
import project.homelearn.repository.user.AttendanceRepository;
import project.homelearn.repository.user.LoginHistoryRepository;
import project.homelearn.repository.user.UserRepository;
import project.homelearn.service.jwt.CookieService;
import project.homelearn.service.jwt.JwtUtil;
import project.homelearn.service.jwt.RedisTokenService;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static project.homelearn.config.security.JwtConstants.*;
import static project.homelearn.entity.student.AttendanceType.*;

@Slf4j
@RequiredArgsConstructor
public class CustomLoginFilter extends UsernamePasswordAuthenticationFilter {

    private final JwtUtil jwtUtil;
    private final CookieService cookieService;
    private final RedisTokenService redisTokenService;
    private final AuthenticationManager authenticationManager;
    private final LoginHistoryRepository loginHistoryRepository;
    private final UserRepository userRepository;
    private final AttendanceRepository attendanceRepository;

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

        // 토큰 생성
        String access = jwtUtil.createJwt(ACCESS_TOKEN_HEADER_NAME, username, Role.valueOf(role), ACCESS_TOKEN_EXPIRATION);
        String refresh = jwtUtil.createJwt(REFRESH_TOKEN_COOKIE_NAME, username, Role.valueOf(role), REFRESH_TOKEN_EXPIRATION);

        // Radis에 refresh 토큰 저장
        redisTokenService.saveRefreshToken(username, refresh, Duration.ofMillis(REFRESH_TOKEN_EXPIRATION));

        /**
         * 응답 설정
         * - Access Token -> Header
         * - Refresh Token -> Cookie
         * - Role -> Header
         */
        response.setHeader(ACCESS_TOKEN_HEADER_NAME, "Bearer " + access);
        response.addCookie(cookieService.createRefreshCookie(REFRESH_TOKEN_COOKIE_NAME, refresh));
        response.setHeader("Role", role);
        response.setStatus(HttpStatus.OK.value());

        User user = userRepository.findByUsername(username);
        loginHistoryRepository.save(new LoginHistory(user));

        if (role.equals("ROLE_STUDENT")) {
            boolean exists = attendanceRepository.existsByUser(user);

            if (!exists) {
                LocalDateTime loginLog = loginHistoryRepository.findUserLoginDateTime(user);
                LocalTime nineFortyAM = LocalTime.of(9, 40);
                LocalTime twoPM =LocalTime.of(14, 0);

                LocalTime loginTime = loginLog.toLocalTime();

                if (loginTime.isBefore(nineFortyAM)) { // 출석
                    Attendance attendance = new Attendance(user, ATTENDANCE, loginLog.toLocalDate());
                    attendanceRepository.save(attendance);

                } else if (loginTime.isBefore(twoPM)) { // 지각
                    Attendance attendance = new Attendance(user, LATE, loginLog.toLocalDate());
                    attendanceRepository.save(attendance);

                } else if (loginTime.isAfter(twoPM)) { // 결석
                    Attendance attendance = new Attendance(user, ABSENT, loginLog.toLocalDate());
                    attendanceRepository.save(attendance);
                }
            }
        }

        log.info("다음 사용자가 로그인 성공 : {}", username);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) {
        log.warn("인증 실패 = {}", failed.getMessage());
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
    }
}