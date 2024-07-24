package project.homelearn.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.homelearn.dto.RegisterDto;
import project.homelearn.entity.student.Student;
import project.homelearn.entity.teacher.Teacher;
import project.homelearn.entity.user.EnrollList;
import project.homelearn.entity.user.Gender;
import project.homelearn.repository.user.EnrollListRepository;
import project.homelearn.repository.user.UserRepository;

import static project.homelearn.entity.user.Role.ROLE_STUDENT;
import static project.homelearn.entity.user.Role.ROLE_TEACHER;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class RegisterService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final EnrollListRepository enrollListRepository;

    public boolean registerProcess(RegisterDto registerDto) {
        Gender gender = registerDto.getGender();
        String email = registerDto.getEmail();

        try {
            if (gender != null) {
                registerStudent(registerDto, gender, email);
            } else {
                registerTeacher(registerDto, email);
            }
            return true;
        } catch (Exception e) {
            log.error("Error registering : ", e);
            return false;
        }
    }

    private void registerStudent(RegisterDto registerDto, Gender gender, String email) {
        Student student = new Student();
        student.setUsername(registerDto.getUsername());
        student.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        student.setName(registerDto.getName());
        student.setGender(gender);
        student.setPhone(registerDto.getPhone());
        student.setEmail(email);

        EnrollList enroll = enrollListRepository.findByEmail(email);
        student.setCurriculum(enroll.getCurriculum());
        student.setRole(ROLE_STUDENT);
        userRepository.save(student);

        deleteEnrollList(email);
    }

    private void registerTeacher(RegisterDto registerDto, String email) {
        Teacher teacher = new Teacher();
        teacher.setUsername(registerDto.getUsername());
        teacher.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        teacher.setName(registerDto.getName());
        teacher.setPhone(registerDto.getPhone());
        teacher.setEmail(email);

        EnrollList enroll = enrollListRepository.findByEmail(email);
        teacher.setCurriculum(enroll.getCurriculum());
        teacher.setRole(ROLE_TEACHER);
        userRepository.save(teacher);

        deleteEnrollList(email);
    }

    private void deleteEnrollList(String email) {
        enrollListRepository.deleteByEmail(email);
    }
}