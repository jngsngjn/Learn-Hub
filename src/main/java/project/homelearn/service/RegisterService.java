package project.homelearn.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.homelearn.dto.RegisterDto;
import project.homelearn.entity.student.Student;
import project.homelearn.repository.user.StudentRepository;
import project.homelearn.repository.user.UserRepository;

import static project.homelearn.entity.user.Role.ROLE_STUDENT;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class RegisterService {

    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public void registerProcess(RegisterDto registerDto) {
        String username = registerDto.getUsername();
        String password = registerDto.getPassword();

        if (userRepository.existsByUsername(username)) {
            log.info("이미 존재하는 아이디로 회원가입 시도 = {}", username);
            return;
        }

        Student student = new Student();
        student.setUsername(username);
        student.setPassword(passwordEncoder.encode(password));
        student.setEmail(registerDto.getEmail());
        student.setRole(ROLE_STUDENT);
        studentRepository.save(student);
    }
}