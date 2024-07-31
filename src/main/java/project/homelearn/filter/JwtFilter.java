package project.homelearn.filter;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import project.homelearn.dto.common.CustomUserDetails;
import project.homelearn.entity.manager.Manager;
import project.homelearn.entity.student.Student;
import project.homelearn.entity.user.Role;
import project.homelearn.entity.user.User;
import project.homelearn.service.jwt.JwtService;

import java.io.IOException;
import java.io.PrintWriter;

import static project.homelearn.config.security.JwtConstants.ACCESS_TOKEN_HEADER_NAME;
import static project.homelearn.entity.user.Role.*;

@Slf4j
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("JWT 필터 호출");

        // 로그인 요청 -> 다음 필터로
        if ("/login".equals(request.getServletPath()) && "POST".equalsIgnoreCase(request.getMethod())) {
            filterChain.doFilter(request, response);
            return;
        }

        // 엑세스 토큰이 없는 경우 (미인증 사용자) -> 다음 필터로
        String accessToken = request.getHeader(ACCESS_TOKEN_HEADER_NAME);
        if (accessToken == null) {
            filterChain.doFilter(request, response);
            return;
        }

        accessToken = accessToken.split(" ")[1];

        // 토큰 만료 여부 확인, 만료 시 다음 필터로 넘기지 않음
        try {
            jwtService.isExpired(accessToken);
        } catch (ExpiredJwtException e) {
            String msg = "Access token is expired";
            writeError(response, msg);
            return;
        }

        // 토큰이 Access 토큰인지 확인, 아니면 다음 필터로 넘기지 않음
        String category = jwtService.getCategory(accessToken);

        if (!category.equals(ACCESS_TOKEN_HEADER_NAME)) {
            String msg = "Access token is invalid";
            writeError(response, msg);
            return;
        }

        // username, role 값을 획득
        User user = null;

        Role role = Role.valueOf(jwtService.getRole(accessToken));

        if (role.equals(ROLE_STUDENT)) {
            user = new Student();
            user.setUsername(jwtService.getUsername(accessToken));
            user.setRole(ROLE_STUDENT);
        }

        if (role.equals(ROLE_MANAGER)) {
            user = new Manager();
            user.setUsername(jwtService.getUsername(accessToken));
            user.setRole(ROLE_MANAGER);
        }

        if (role.equals(ROLE_TEACHER)) {
            user = new Manager();
            user.setUsername(jwtService.getUsername(accessToken));
            user.setRole(ROLE_TEACHER);
        }

        // 임시 세션에 저장
        CustomUserDetails customUserDetails = new CustomUserDetails(user);
        Authentication authToken = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authToken);

        log.info("JWT 검증 완료, SecurityContext에 인증 정보 저장");
        filterChain.doFilter(request, response);
    }

    private void writeError(HttpServletResponse response, String msg) throws IOException {
        PrintWriter writer = response.getWriter();
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        writer.print(msg);
        writer.flush();
        writer.close();
    }
}