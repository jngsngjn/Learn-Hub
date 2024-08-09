package project.homelearn.service.student;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.homelearn.dto.student.inquiry.StudentInquiryDetailDto;
import project.homelearn.dto.student.inquiry.StudentInquiryDto;
import project.homelearn.dto.student.inquiry.StudentInquiryListDto;
import project.homelearn.entity.inquiry.ManagerInquiry;
import project.homelearn.entity.inquiry.TeacherInquiry;
import project.homelearn.entity.student.Student;
import project.homelearn.repository.inquiry.ManagerInquiryRepository;
import project.homelearn.repository.inquiry.TeacherInquiryRepository;
import project.homelearn.repository.user.StudentRepository;
import project.homelearn.service.common.InquiryNotificationService;

import java.util.ArrayList;
import java.util.List;

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
    private final InquiryNotificationService inquiryNotificationService;
    private final TeacherInquiryRepository teacherInquiryRepository;

    public void inquiryToManger(String username, StudentInquiryDto inquiryDto) {
        Student student = studentRepository.findByUsername(username);

        ManagerInquiry inquiry = new ManagerInquiry();
        inquiry.setTitle(inquiryDto.getTitle());
        inquiry.setContent(inquiryDto.getContent());
        inquiry.setUser(student);
        managerInquiryRepository.save(inquiry);

        // 매니저에게 알림
        inquiryNotificationService.notifyToMangerStudentInquiry(inquiry);
    }

    public boolean modifyManagerInquiry(Long inquiryId, String username, StudentInquiryDto inquiryDto) {
        ManagerInquiry inquiry = managerInquiryRepository.findById(inquiryId).orElseThrow();
        String writer = inquiry.getUser().getUsername();

        if (!writer.equals(username)) {
            log.info("[학생] 매니저 문의 수정 : 작성자와 수정자가 같지 않음!, 작성자 = {}, 수정자 = {}", writer, username);
            return false;
        }

        if (inquiry.getResponse() != null) {
            log.info("[학생] 매니저 문의 수정 : 이미 답변이 된 문의임!");
            return false;
        }

        inquiry.setTitle(inquiryDto.getTitle());
        inquiry.setContent(inquiryDto.getContent());
        return true;
    }

    public boolean deleteManagerInquiry(Long inquiryId, String username) {
        ManagerInquiry inquiry = managerInquiryRepository.findById(inquiryId).orElseThrow();
        String writer = inquiry.getUser().getUsername();

        if (!writer.equals(username)) {
            return false;
        }

        managerInquiryRepository.deleteById(inquiryId);
        return true;
    }


    public StudentInquiryDetailDto getMyManagerInquiryDetail(Long inquiryId){
        ManagerInquiry inquiry = managerInquiryRepository.findById(inquiryId).orElseThrow();

        return new StudentInquiryDetailDto(
                inquiry.getId(),
                inquiry.getUser().getName(),
                inquiry.getUser().getCurriculum().getName(),
                inquiry.getUser().getCurriculum().getTh(),
                inquiry.getTitle(),
                inquiry.getContent(),
                inquiry.getCreatedDate(),
                inquiry.getResponse() != null ? inquiry.getResponse() : null
        );
    }

    public List<StudentInquiryListDto> getMyManagerInquiryList(String username){
        List<ManagerInquiry> inquiries = managerInquiryRepository.findStudentInquiry(username);

        return getDtoList(inquiries);
    }

    public List<StudentInquiryListDto> getMyTeacherInquiryList(String username){
        List<TeacherInquiry> inquiries = teacherInquiryRepository.findStudentInquiry(username);

        return getDtoList(inquiries);
    }

    private List<StudentInquiryListDto> getDtoList(List<?> inquiries) {
        List<StudentInquiryListDto> dtoList = new ArrayList<>();

        for (Object inquiry : inquiries) {
            if (inquiry instanceof ManagerInquiry managerInquiry) {
                dtoList.add(new StudentInquiryListDto(
                        managerInquiry.getId(),
                        managerInquiry.getTitle(),
                        managerInquiry.getUser().getName(),
                        managerInquiry.getUser().getCurriculum().getName(),
                        managerInquiry.getUser().getCurriculum().getTh(),
                        managerInquiry.getCreatedDate(),
                        managerInquiry.getResponse()
                ));
            } else if (inquiry instanceof TeacherInquiry teacherInquiry) {
                dtoList.add(new StudentInquiryListDto(
                        teacherInquiry.getId(),
                        teacherInquiry.getTitle(),
                        teacherInquiry.getUser().getName(),
                        teacherInquiry.getUser().getCurriculum().getName(),
                        teacherInquiry.getUser().getCurriculum().getTh(),
                        teacherInquiry.getCreatedDate(),
                        teacherInquiry.getResponse()
                ));
            }
        }

        return dtoList;
    }

    public void inquiryToTeacher(String username, StudentInquiryDto inquiryDto) {
        Student student = studentRepository.findByUsername(username);

        TeacherInquiry inquiry = new TeacherInquiry();
        inquiry.setTitle(inquiryDto.getTitle());
        inquiry.setContent(inquiryDto.getContent());
        inquiry.setUser(student);
        teacherInquiryRepository.save(inquiry);

        // 강사에게 알림
        inquiryNotificationService.notifyToTeacherStudentInquiry(inquiry);
    }

    public boolean modifyTeacherInquiry(Long inquiryId, String username, StudentInquiryDto inquiryDto) {
        TeacherInquiry inquiry = teacherInquiryRepository.findById(inquiryId).orElseThrow();
        String writer = inquiry.getUser().getUsername();

        if (!writer.equals(username)) {
            log.info("[학생] 강사 문의 수정 : 작성자와 수정자가 같지 않음!, 작성자 = {}, 수정자 = {}", writer, username);
            return false;
        }

        if (inquiry.getResponse() != null) {
            log.info("[학생] 강사 문의 수정 : 이미 답변이 된 문의임!");
            return false;
        }

        inquiry.setTitle(inquiryDto.getTitle());
        inquiry.setContent(inquiryDto.getContent());
        return true;
    }

    public boolean deleteTeacherInquiry(Long inquiryId, String username) {
        TeacherInquiry inquiry = teacherInquiryRepository.findById(inquiryId).orElseThrow();
        String writer = inquiry.getUser().getUsername();

        if (!writer.equals(username)) {
            return false;
        }

        teacherInquiryRepository.deleteById(inquiryId);
        return true;
    }

    public StudentInquiryDetailDto getMyTeacherInquiryDetail(Long inquiryId){
        TeacherInquiry inquiry = teacherInquiryRepository.findById(inquiryId).orElseThrow();

        return new StudentInquiryDetailDto(
                inquiry.getId(),
                inquiry.getUser().getName(),
                inquiry.getUser().getCurriculum().getName(),
                inquiry.getUser().getCurriculum().getTh(),
                inquiry.getTitle(),
                inquiry.getContent(),
                inquiry.getCreatedDate(),
                inquiry.getResponse() != null ? inquiry.getResponse() : null
        );
    }


}