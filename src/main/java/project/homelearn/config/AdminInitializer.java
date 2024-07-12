package project.homelearn.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import project.homelearn.entity.admin.Admin;
import project.homelearn.repository.UserRepository;

import static project.homelearn.entity.user.Role.ROLE_ADMIN;

@Component
public class AdminInitializer implements CommandLineRunner {

    private final String adminPassword;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public AdminInitializer(@Value("${admin.password}") String adminPassword, UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.adminPassword = adminPassword;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        if (!userRepository.existsByUsername("admin")) {
            Admin admin = new Admin();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode(adminPassword));
            admin.setRole(ROLE_ADMIN);
            userRepository.save(admin);
        }
    }
}