package project.homelearn.service.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import project.homelearn.entity.user.Role;

import java.time.Duration;

import static project.homelearn.config.security.JwtConstants.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReissueService {

    private final JwtUtil jwtUtil;
    private final CookieService cookieService;
    private final RedisTokenService redisTokenService;

    public String checkRefreshToken(HttpServletRequest request) {

        String refreshToken = cookieService.getRefreshToken(request);

        if (refreshToken == null) {
            log.info("Refresh token is null");
            return null;
        }

        try {
            jwtUtil.isExpired(refreshToken);
        } catch (ExpiredJwtException e) {
            log.info("Refresh token is expired");
            return "expired";
        }

        String category = jwtUtil.getCategory(refreshToken);

        if (!category.equals("refresh")) {
            log.info("Refresh token is invalid");
            return "invalid";
        }

        String username = jwtUtil.getUsername(refreshToken);
        String storedToken = redisTokenService.getRefreshToken(username);

        if (!refreshToken.equals(storedToken)) {
            log.info("Refresh token is invalid");
            return "invalid";
        }

        return "ok " + refreshToken;
    }

    public void reissueRefreshToken(String refreshToken, HttpServletResponse response) {
        String username = jwtUtil.getUsername(refreshToken);
        String role = jwtUtil.getRole(refreshToken);

        // 새로운 토큰 발급 (rotate)
        String newAccess = jwtUtil.createJwt(ACCESS_TOKEN_HEADER_NAME, username, Role.valueOf(role), ACCESS_TOKEN_EXPIRATION);
        String newRefresh = jwtUtil.createJwt(REFRESH_TOKEN_COOKIE_NAME, username, Role.valueOf(role), REFRESH_TOKEN_EXPIRATION);

        // Redis에 기존 Refresh 토큰을 삭제하고 새로운 Refresh 토큰 저장
        redisTokenService.deleteByRefreshToken(refreshToken);
        redisTokenService.saveRefreshToken(username, newRefresh, Duration.ofMillis(REFRESH_TOKEN_EXPIRATION));

        response.setHeader(ACCESS_TOKEN_HEADER_NAME, "Bearer " + newAccess);
        response.addCookie(cookieService.createRefreshCookie(REFRESH_TOKEN_COOKIE_NAME, newRefresh));
    }
}