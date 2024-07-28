package project.homelearn.service.student;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class StudentInquiryService {

    private final StudentRepository studentRepository;
    private final ManagerInquiryRepository managerInquiryRepository;
    private final ManagerNotificationService managerNotificationService;

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

    public boolean modifyManagerInquiry(Long inquiryId, String username, StudentToMangerInquiryDto inquiryDto) {
        String writer = studentRepository.findUsernameByInquiryId(inquiryId);

        if (!writer.equals(username)) {
            log.info("[학생] 매니저 문의 수정 : 작성자와 수정자가 같지 않음!, 작성자 = {}, 수정자 = {}", writer, username);
            return false;
        }

        ManagerInquiry inquiry = managerInquiryRepository.findById(inquiryId).orElseThrow();
        if (inquiry.getResponse() != null) {
            log.info("[학생] 매니저 문의 수정 : 이미 답변이 된 문의임!");
            return false;
        }

        inquiry.setTitle(inquiryDto.getTitle());
        inquiry.setContent(inquiryDto.getContent());
        return true;
    }
}