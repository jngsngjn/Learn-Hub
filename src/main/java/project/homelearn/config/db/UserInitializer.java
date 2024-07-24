package project.homelearn.config.db;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import project.homelearn.entity.manager.Manager;
import project.homelearn.entity.teacher.Teacher;
import project.homelearn.repository.user.UserRepository;

import static project.homelearn.entity.user.Role.ROLE_MANAGER;
import static project.homelearn.entity.user.Role.ROLE_TEACHER;

@Component
public class UserInitializer implements CommandLineRunner {

    private final String password;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserInitializer(@Value("${manager.password}") String password, UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.password = password;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        if (!userRepository.existsByUsername("manager")) {
            Manager manager = new Manager();
            manager.setUsername("manager");
            manager.setPassword(passwordEncoder.encode(password));
            manager.setRole(ROLE_MANAGER);
            userRepository.save(manager);
        }

        if (!userRepository.existsByUsername("teacher")) {
            Teacher teacher = new Teacher();
            teacher.setUsername("teacher");
            teacher.setPassword(passwordEncoder.encode(password));
            teacher.setRole(ROLE_TEACHER);
            userRepository.save(teacher);
        }

    }
}