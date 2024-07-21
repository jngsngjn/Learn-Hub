package project.homelearn.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import project.homelearn.entity.manager.Manager;
import project.homelearn.repository.user.UserRepository;

import static project.homelearn.entity.user.Role.ROLE_MANAGER;

@Component
public class ManagerInitializer implements CommandLineRunner {

    private final String managerPassword;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public ManagerInitializer(@Value("${manager.password}") String managerPassword, UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.managerPassword = managerPassword;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        if (!userRepository.existsByUsername("manager")) {
            Manager manager = new Manager();
            manager.setUsername("manager");
            manager.setPassword(passwordEncoder.encode(managerPassword));
            manager.setRole(ROLE_MANAGER);
            userRepository.save(manager);
        }
    }
}