package project.homelearn.service.teacher;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import project.homelearn.dto.manager.inquiry.ManagerInquiryDto;
import project.homelearn.dto.manager.inquiry.ManagerResponseDto;
import project.homelearn.dto.teacher.inquiry.TeacherInquiryDto;
import project.homelearn.dto.teacher.inquiry.TeacherResponseDto;
import project.homelearn.entity.inquiry.ManagerInquiry;
import project.homelearn.entity.inquiry.TeacherInquiry;
import project.homelearn.entity.user.Role;
import project.homelearn.repository.inquiry.TeacherInquiryRepository;
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

    // 문의내역 리스트(학생)
    public List<TeacherInquiryDto> getInquiryListDefaultFromStudents() {
        List<TeacherInquiry> teacherInquiries = teacherInquiryRepository.findStudentInquiriesAllDefault();

        return getManagerInquiryDtoList(teacherInquiries);
    }

    // 1:1 문의 개수
    public Integer getInquiryCount() {
        return teacherInquiryRepository.countInquiryWithOutResponse();
    }

    // 문의 내역 상세보기
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

    // 매니저가 문의 내역 답변달기
    public boolean addResponse(TeacherResponseDto teacherResponseDto, Long inquiryId) {
        Optional<TeacherInquiry> teacherInquiry = teacherInquiryRepository.findById(inquiryId);

        if (teacherInquiry.isPresent()) {
            TeacherInquiry inquiry = teacherInquiry.get();
            inquiry.setResponse(teacherResponseDto.getResponse());
            inquiry.setResponseDate(LocalDateTime.now());
            teacherInquiryRepository.save(inquiry);

            commonNotificationService.notifyTeacherResponse(inquiry); // 학생 또는 강사에게 알림
            return true;
        }
        return false;
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
