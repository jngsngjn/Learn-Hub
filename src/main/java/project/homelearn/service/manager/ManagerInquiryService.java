package project.homelearn.service.manager;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import project.homelearn.dto.manager.inquiry.ManagerInquiryDto;
import project.homelearn.dto.manager.inquiry.ManagerResponseDto;
import project.homelearn.entity.inquiry.ManagerInquiry;
import project.homelearn.repository.inquiry.ManagerInquiryRepository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ManagerInquiryService {

    private final ManagerInquiryRepository managerInquiryRepository;

    /**
    * 강사 <- 문의
     * Author : 김승민
    * */

    //문의내역 리스트(학생)
    public List<ManagerInquiryDto> getInquiryListDefaultFromStudents() {
        List<ManagerInquiry> managerInquiries = managerInquiryRepository.findStudentInquiriesAllDefault();

        return getManagerInquiryDtoList(managerInquiries);
    }

    //문의내역 커리큘럼 이름 기준(학생)
    public List<ManagerInquiryDto> getInquiryListWithCurriculumNameFromStudents(String curriculumName) {
        List<ManagerInquiry> managerInquiries = managerInquiryRepository.findStudentInquiriesWithCurriculumName(curriculumName);

        return getManagerInquiryDtoList(managerInquiries);
    }

    //문의내역 커리큘럼 이름 + 기수 기준(학생)
    public List<ManagerInquiryDto> getInquiryListWithCurriculumNameAndThFromStudents(String curriculumName, Long curriculumTh) {
        List<ManagerInquiry> managerInquiries = managerInquiryRepository.findStudentInquiriesWithCurriculumNameAndCurriculumTh(curriculumName, curriculumTh);

        return getManagerInquiryDtoList(managerInquiries);
    }



    //문의내역 리스트(강사)
    public List<ManagerInquiryDto> getInquiryListDefaultFromTeachers() {
        List<ManagerInquiry> managerInquiries = managerInquiryRepository.findTeacherInquiresAllDefault();

        return getManagerInquiryDtoList(managerInquiries);
    }

    //문의내역 커리큘럼 이름 기준(강사)
    public List<ManagerInquiryDto> getInquiryListWithCurriculumNameFromTeachers(String curriculumName) {
        List<ManagerInquiry> managerInquiries = managerInquiryRepository.findTeacherInquiriesWithCurriculumName(curriculumName);

        return getManagerInquiryDtoList(managerInquiries);
    }

    //문의내역 커리큘럼 이름 + 기수 기준(강사)
    public List<ManagerInquiryDto> getInquiryListWithCurriculumNameAndThFromTeachers(String curriculumName, Long curriculumTh) {
        List<ManagerInquiry> managerInquiries = managerInquiryRepository.findTeacherInquiriesWithCurriculumNameAndCurriculumTh(curriculumName, curriculumTh);

        return getManagerInquiryDtoList(managerInquiries);
    }

    //문의 내역 상세보기
    public ManagerInquiryDto getOneManagerInquiryDtoById(Long id) {
        Optional<ManagerInquiry> managerInquiry = managerInquiryRepository.findById(id);

        if (managerInquiry.isPresent()) {
            ManagerInquiry inquiry = managerInquiry.get();
            return new ManagerInquiryDto(
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

    //매니저가 문의 내역 답변달기
    public boolean addResponse(ManagerResponseDto managerResponseDto, Long inquiryId){
        Optional<ManagerInquiry> managerInquiry = managerInquiryRepository.findById(inquiryId);

        if (managerInquiry.isPresent()) {
            ManagerInquiry inquiry = managerInquiry.get();
            inquiry.setResponse(managerResponseDto.getResponse());
            inquiry.setResponseDate(managerResponseDto.getResponseDate());
            managerInquiryRepository.save(inquiry);
            return true;
        }
        return false;
    }

    //리스트 Dto 변환 메소드
    private static List<ManagerInquiryDto> getManagerInquiryDtoList(List<ManagerInquiry> managerInquiries) {
        return managerInquiries.stream()
                .map(managerInquiry -> new ManagerInquiryDto(
                        managerInquiry.getId(),
                        managerInquiry.getTitle(),
                        managerInquiry.getContent(),
                        managerInquiry.getCreatedDate(),
                        managerInquiry.getUser(),
                        managerInquiry.getResponse() != null ? managerInquiry.getResponse() : null,
                        managerInquiry.getResponseDate() != null ? managerInquiry.getResponseDate() : null
                ))
                .toList();
    }


}
