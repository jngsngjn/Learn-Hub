package project.homelearn.filter;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.GenericFilterBean;
import project.homelearn.service.jwt.CookieService;
import project.homelearn.service.jwt.JwtService;
import project.homelearn.service.common.RedisService;

import java.io.IOException;

import static project.homelearn.config.security.JwtConstants.REFRESH_TOKEN_COOKIE_NAME;

@Slf4j
@RequiredArgsConstructor
public class CustomLogoutFilter extends GenericFilterBean {

    private final JwtService jwtService;
    private final RedisService redisService;
    private final CookieService cookieService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // 로그아웃 경로와 HTTP 메서드 검증
        if (isLogoutRequest(httpRequest)) {
            handleLogout(httpRequest, httpResponse);
        } else {
            filterChain.doFilter(request, response);
        }
    }

    private boolean isLogoutRequest(HttpServletRequest request) {
        return request.getRequestURI().matches("^\\/logout$") && request.getMethod().equals("POST");
    }

    private void handleLogout(HttpServletRequest request, HttpServletResponse response) {
        String refresh = cookieService.getRefreshToken(request);

        if (refresh == null || isTokenInvalid(refresh)) {
            log.info("Refresh token is invalid or expired");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        String username = jwtService.getUsername(refresh);
        if (isStoredTokenInvalid(username, refresh)) {
            log.info("Stored refresh token is invalid");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        // 로그아웃 진행
        redisService.deleteRefreshToken(username);
        cookieService.clearCookie(REFRESH_TOKEN_COOKIE_NAME, response);

        response.setStatus(HttpServletResponse.SC_OK);
        log.info("다음 사용자가 로그아웃 성공 : {}", username);
    }

    private boolean isTokenInvalid(String refresh) {
        try {
            jwtService.isExpired(refresh);
        } catch (ExpiredJwtException e) {
            return true;
        }

        return !jwtService.getCategory(refresh).equals(REFRESH_TOKEN_COOKIE_NAME);
    }

    private boolean isStoredTokenInvalid(String username, String refresh) {
        String storedToken = redisService.getRefreshToken(username);
        return !refresh.equals(storedToken);
    }
}