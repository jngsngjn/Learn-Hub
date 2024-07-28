package project.homelearn.service.student;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.homelearn.dto.student.inquiry.StudentToMangerInquiryDto;
import project.homelearn.entity.inquiry.ManagerInquiry;
import project.homelearn.entity.student.Student;
import project.homelearn.repository.inquiry.ManagerInquiryRepository;
import project.homelearn.repository.user.StudentRepository;
import project.homelearn.service.manager.ManagerNotificationService;

/**
 * Author : 정성진
 */
@Service
@Transactional
@RequiredArgsConstructor
public class StudentInquiryService {

    private final StudentRepository studentRepository;
    private final ManagerNotificationService managerNotificationService;
    private final ManagerInquiryRepository managerInquiryRepository;

    public void inquiryToManger(String username, StudentToMangerInquiryDto inquiryDto) {
        Student student = studentRepository.findByUsername(username);

        ManagerInquiry inquiry = new ManagerInquiry();
        inquiry.setTitle(inquiryDto.getTitle());
        inquiry.setContent(inquiryDto.getContent());
        inquiry.setUser(student);
        managerInquiryRepository.save(inquiry);

        // 매니저에게 알림
        managerNotificationService.notifyStudentInquiry(inquiry);
    }
}