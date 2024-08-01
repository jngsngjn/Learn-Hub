package project.homelearn.service.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import project.homelearn.entity.user.Role;
import project.homelearn.service.common.RedisService;

import java.time.Duration;

import static project.homelearn.config.security.JwtConstants.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReissueService {

    private final JwtService jwtService;
    private final CookieService cookieService;
    private final RedisService redisService;

    public String checkRefreshToken(HttpServletRequest request) {

        String refreshToken = cookieService.getRefreshToken(request);

        if (refreshToken == null) {
            log.info("Refresh token is null");
            return null;
        }

        try {
            jwtService.isExpired(refreshToken);
        } catch (ExpiredJwtException e) {
            log.info("Refresh token is expired");
            return "expired";
        }

        String category = jwtService.getCategory(refreshToken);

        if (!category.equals("refresh")) {
            log.info("Refresh token is invalid");
            return "invalid";
        }

        String username = jwtService.getUsername(refreshToken);
        String storedToken = redisService.getRefreshToken(username);

        if (!refreshToken.equals(storedToken)) {
            log.info("Refresh token is invalid");
            return "invalid";
        }

        return "ok " + refreshToken;
    }

    public void reissueRefreshToken(String refreshToken, HttpServletResponse response) {
        String username = jwtService.getUsername(refreshToken);
        String role = jwtService.getRole(refreshToken);

        // 새로운 토큰 발급 (rotate)
        String newAccess = jwtService.createJwt(ACCESS_TOKEN_HEADER_NAME, username, Role.valueOf(role), ACCESS_TOKEN_EXPIRATION);
        String newRefresh = jwtService.createJwt(REFRESH_TOKEN_COOKIE_NAME, username, Role.valueOf(role), REFRESH_TOKEN_EXPIRATION);

        // Redis에 기존 Refresh 토큰을 삭제하고 새로운 Refresh 토큰 저장
        redisService.deleteByRefreshToken(refreshToken);
        redisService.saveRefreshToken(username, newRefresh, Duration.ofMillis(REFRESH_TOKEN_EXPIRATION));

        response.setHeader(ACCESS_TOKEN_HEADER_NAME, "Bearer " + newAccess);
        response.addCookie(cookieService.createRefreshCookie(REFRESH_TOKEN_COOKIE_NAME, newRefresh));
    }
}