package project.homelearn.service.teacher;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import project.homelearn.dto.common.inquiry.InquiryWriteDto;
import project.homelearn.dto.teacher.inquiry.TeacherInquiryDto;
import project.homelearn.dto.teacher.inquiry.TeacherResponseDto;
import project.homelearn.entity.curriculum.Curriculum;
import project.homelearn.entity.inquiry.ManagerInquiry;
import project.homelearn.entity.inquiry.TeacherInquiry;
import project.homelearn.entity.teacher.Teacher;
import project.homelearn.entity.user.User;
import project.homelearn.repository.inquiry.ManagerInquiryRepository;
import project.homelearn.repository.inquiry.TeacherInquiryRepository;
import project.homelearn.repository.user.TeacherRepository;
import project.homelearn.repository.user.UserRepository;
import project.homelearn.service.common.CommonNotificationService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Author : 김승민
 */
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class TeacherInquiryService {

    private final CommonNotificationService commonNotificationService;
    private final TeacherInquiryRepository teacherInquiryRepository;
    private final ManagerInquiryRepository managerInquiryRepository;
    private final UserRepository userRepository;
    private final TeacherRepository teacherRepository;

    // 문의 내역 리스트(학생)
    public List<TeacherInquiryDto> getInquiryListDefaultFromStudents() {
        List<TeacherInquiry> teacherInquiries = teacherInquiryRepository.findStudentInquiriesAllDefault();

        return getManagerInquiryDtoList(teacherInquiries);
    }

    // 1:1 문의 개수
    public Integer getStudentInquiryCount(String username) {
        Teacher teacher = teacherRepository.findByUsernameAndCurriculum(username);
        Curriculum curriculum = teacher.getCurriculum();

        return teacherInquiryRepository.findStudentInquiryCount(curriculum);
    }

    // 문의 내역 상세 조회
    public TeacherInquiryDto getOneManagerInquiryDtoById(Long inquiryId) {
        Optional<TeacherInquiry> teacherInquiry = teacherInquiryRepository.findById(inquiryId);

        if (teacherInquiry.isPresent()) {
            TeacherInquiry inquiry = teacherInquiry.get();
            return new TeacherInquiryDto(
                    inquiry.getId(),
                    inquiry.getTitle(),
                    inquiry.getContent(),
                    inquiry.getCreatedDate(),
                    inquiry.getUser(),
                    inquiry.getResponse(),
                    inquiry.getResponseDate()
            );
        }
        return null;
    }

    // 학생 문의에 답변
    public boolean addResponseToStudent(TeacherResponseDto teacherResponseDto, Long inquiryId) {
        Optional<TeacherInquiry> teacherInquiry = teacherInquiryRepository.findById(inquiryId);

        if (teacherInquiry.isPresent()) {
            TeacherInquiry inquiry = teacherInquiry.get();
            inquiry.setResponse(teacherResponseDto.getResponse());
            inquiry.setResponseDate(LocalDateTime.now());
            teacherInquiryRepository.save(inquiry);

            commonNotificationService.notifyTeacherResponse(inquiry); // 학생에게 알림
            return true;
        }
        return false;
    }

    // 매니저에게 문의 작성
    public boolean writeInquiryToManager(String username, InquiryWriteDto writeDto){
        try{
            ManagerInquiry managerInquiry = new ManagerInquiry();
            User teacher = userRepository.findByUsername(username);
            managerInquiry.setUser(teacher);
            managerInquiry.setTitle(writeDto.getTitle());
            managerInquiry.setContent(writeDto.getContent());

            managerInquiryRepository.save(managerInquiry);
            return true;
        }
        catch (Exception e){
            log.error("Error creating inquiry to manager", e);
            return false;
        }
    }

    // 리스트 Dto 변환 메소드
    private static List<TeacherInquiryDto> getManagerInquiryDtoList(List<TeacherInquiry> teacherInquiries) {
        return teacherInquiries.stream()
                .map(teacherInquiry -> new TeacherInquiryDto(
                        teacherInquiry.getId(),
                        teacherInquiry.getTitle(),
                        teacherInquiry.getContent(),
                        teacherInquiry.getCreatedDate(),
                        teacherInquiry.getUser(),
                        teacherInquiry.getResponse() != null ? teacherInquiry.getResponse() : null,
                        teacherInquiry.getResponseDate() != null ? teacherInquiry.getResponseDate() : null
                ))
                .toList();
    }
}
