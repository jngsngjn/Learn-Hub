package project.homelearn.service.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import project.homelearn.service.jwt.JwtService;

import java.time.Duration;

@Service
public class RedisService {

    private final RedisTemplate<String, String> redisTemplate;
    private final ValueOperations<String, String> valueOperations;
    private final JwtService jwtService;

    @Autowired
    public RedisService(RedisTemplate<String, String> redisTemplate, JwtService jwtService) {
        this.redisTemplate = redisTemplate;
        this.valueOperations = redisTemplate.opsForValue();
        this.jwtService = jwtService;
    }

    public void saveRefreshToken(String username, String refreshToken, Duration duration) {
        valueOperations.set(username, refreshToken, duration);
    }

    public String getRefreshToken(String username) {
        return valueOperations.get(username);
    }

    public void deleteRefreshToken(String username) {
        redisTemplate.delete(username);
    }

    public void deleteByRefreshToken(String refreshToken) {
        String username = jwtService.getUsername(refreshToken);
        deleteRefreshToken(username);
    }

    public void saveEmailCode(String email, String code, Duration duration) {
        valueOperations.set(email, code, duration);
    }

    public String getCodeByEmail(String email) {
        return valueOperations.get(email);
    }

    public void deleteEmailCode(String email) {
        redisTemplate.delete(email);
    }

    public void markUserAsVerified(String username, Duration duration) {
        valueOperations.set("verified:" + username, "true", duration);
    }

    public boolean isUserVerified(String username) {
        String value = valueOperations.get("verified:" + username);
        return "true".equals(value);
    }

    public void deleteVerification(String username) {
        redisTemplate.delete("verified:" + username);
    }
}