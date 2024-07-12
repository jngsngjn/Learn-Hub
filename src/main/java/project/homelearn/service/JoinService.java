package project.homelearn.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.homelearn.dto.JoinDto;
import project.homelearn.entity.student.Student;
import project.homelearn.repository.StudentRepository;
import project.homelearn.repository.UserRepository;

import static project.homelearn.entity.user.Role.ROLE_STUDENT;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class JoinService {

    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public void joinProcess(JoinDto joinDto) {
        String username = joinDto.getUsername();
        String password = joinDto.getPassword();

        if (userRepository.existsByUsername(username)) {
            log.info("이미 존재하는 아이디로 회원가입 시도 = {}", username);
            return;
        }

        Student student = new Student();
        student.setUsername(username);
        student.setPassword(passwordEncoder.encode(password));
        student.setEmail(joinDto.getEmail());
        student.setRole(ROLE_STUDENT);
        studentRepository.save(student);
    }
}