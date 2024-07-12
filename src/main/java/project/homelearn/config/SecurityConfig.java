package project.homelearn.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.web.cors.CorsConfiguration;
import project.homelearn.filter.CustomLogoutFilter;
import project.homelearn.filter.JwtFilter;
import project.homelearn.filter.CustomLoginFilter;
import project.homelearn.service.jwt.CookieService;
import project.homelearn.service.jwt.JwtUtil;
import project.homelearn.service.jwt.RedisTokenService;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtUtil jwtUtil;
    private final CookieService cookieService;
    private final RedisTokenService redisTokenService;
    private final AuthenticationConfiguration authenticationConfiguration;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf(auth -> auth.disable());
        http.formLogin(auth -> auth.disable());
        http.httpBasic(auth -> auth.disable());

        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/login", "/join", "/reissue").permitAll()
                .requestMatchers("/admin").hasRole("ADMIN")
                .requestMatchers("/student").hasRole("STUDENT")
                .anyRequest().authenticated()
        );

        // JWT 사용 위해 세션을 무상태로 설정
        http.sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // 커스텀 필터 등록 (JwtFilter -> CustomLoginFilter -> CustomLogoutFilter -> LogoutFilter)
        http.addFilterBefore(new JwtFilter(jwtUtil), CustomLoginFilter.class);
        http.addFilterAt(new CustomLoginFilter(jwtUtil, cookieService, redisTokenService, authenticationManager(authenticationConfiguration)),
                UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(new CustomLogoutFilter(jwtUtil, redisTokenService, cookieService), LogoutFilter.class);

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}