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
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import project.homelearn.filter.CustomLoginFilter;
import project.homelearn.filter.CustomLogoutFilter;
import project.homelearn.filter.JwtFilter;
import project.homelearn.repository.user.LoginHistoryRepository;
import project.homelearn.repository.user.UserRepository;
import project.homelearn.service.jwt.CookieService;
import project.homelearn.service.jwt.JwtUtil;
import project.homelearn.service.jwt.RedisTokenService;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtUtil jwtUtil;
    private final CookieService cookieService;
    private final RedisTokenService redisTokenService;
    private final AuthenticationConfiguration authenticationConfiguration;
    private final LoginHistoryRepository loginHistoryRepository;
    private final UserRepository userRepository;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // CSRF 설정
//        http.csrf(csrf -> csrf
//                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
//        );

        http.csrf(csrf -> csrf.disable());

        http.formLogin(auth -> auth.disable());
        http.httpBasic(auth -> auth.disable());

        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/login", "/register/**", "/code-verify","/reissue", "/csrf-token").permitAll()
                .requestMatchers("/manager/**").hasRole("MANAGER")
                .requestMatchers("/teacher/**").hasRole("TEACHER")
                .requestMatchers("/student/**").hasRole("STUDENT")
                .anyRequest().authenticated()
        );

        // JWT 사용 위해 세션을 무상태로 설정
        http.sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // 커스텀 필터 등록 (JwtFilter -> CustomLoginFilter -> CustomLogoutFilter -> LogoutFilter)
        http.addFilterBefore(new JwtFilter(jwtUtil), CustomLoginFilter.class);
        http.addFilterAt(new CustomLoginFilter(jwtUtil, cookieService, redisTokenService, authenticationManager(authenticationConfiguration), loginHistoryRepository, userRepository),
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