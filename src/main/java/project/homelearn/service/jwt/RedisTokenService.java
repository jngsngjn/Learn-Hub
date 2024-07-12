package project.homelearn.service.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class RedisTokenService {

    private final RedisTemplate<String, String> redisTemplate;
    private final ValueOperations<String, String> valueOperations;
    private final JwtUtil jwtUtil;

    @Autowired
    public RedisTokenService(RedisTemplate<String, String> redisTemplate, JwtUtil jwtUtil) {
        this.redisTemplate = redisTemplate;
        this.valueOperations = redisTemplate.opsForValue();
        this.jwtUtil = jwtUtil;
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
        String username = jwtUtil.getUsername(refreshToken);
        deleteRefreshToken(username);
    }
}