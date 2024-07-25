package project.homelearn.service.manager;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import project.homelearn.dto.manager.inquiry.ManagerResponseDto;
import project.homelearn.entity.curriculum.Curriculum;
import project.homelearn.entity.curriculum.CurriculumType;
import project.homelearn.entity.inquiry.ManagerInquiry;
import project.homelearn.entity.student.Student;
import project.homelearn.entity.user.Gender;
import project.homelearn.entity.user.Role;
import project.homelearn.repository.curriculum.CurriculumRepository;
import project.homelearn.repository.inquiry.ManagerInquiryRepository;
import project.homelearn.repository.user.StudentRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class ManagerInquiryServiceTest {


    @Autowired
    ManagerInquiryRepository managerInquiryRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    CurriculumRepository curriculumRepository;

    @Autowired
    ManagerInquiryService managerInquiryService;

    @BeforeEach
    void init() {

        Curriculum curriculum = new Curriculum();
        curriculum.setColor("color");
        curriculum.setName("name");
        curriculum.setTh(1L);
        curriculum.setFullName("full name");
        curriculum.setStartDate(LocalDate.now());
        curriculum.setEndDate(LocalDate.now().plusDays(1));
        curriculum.setType(CurriculumType.NCP);

        curriculumRepository.save(curriculum);

        Student student = new Student();
        student.setUsername("aaa");
        student.setPassword("password");
        student.setName("name");
        student.setGender(Gender.FEMALE);
        student.setEmail("email");
        student.setPhone("phone");
        student.setCurriculum(curriculum);
        student.setRole(Role.ROLE_STUDENT);
        studentRepository.save(student);

        ManagerInquiry managerInquiry = new ManagerInquiry();
        managerInquiry.setTitle("title");
        managerInquiry.setContent("content");
        managerInquiry.setUser(student);
        managerInquiryRepository.save(managerInquiry);
    }

    @Test
    void test() {
        ManagerResponseDto managerResponseDto = new ManagerResponseDto("response", LocalDateTime.now());
        boolean result = managerInquiryService.addResponse(managerResponseDto, 1L);

        assertThat(result).isTrue();
    }
}