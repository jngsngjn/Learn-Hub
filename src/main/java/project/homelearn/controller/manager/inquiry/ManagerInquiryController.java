package project.homelearn.controller.manager.inquiry;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.homelearn.dto.manager.inquiry.ManagerInquiryDto;
import project.homelearn.dto.manager.inquiry.ManagerResponseDto;
import project.homelearn.service.manager.ManagerInquiryService;

import java.util.List;

/**
 * Author : 김승민
 */
@Slf4j
@RestController
@RequestMapping("/managers")
@RequiredArgsConstructor
public class ManagerInquiryController {

    private final ManagerInquiryService managerInquiryService;

    // 학생 문의 리스트 조회
    @GetMapping("/students-inquiries")
    public ResponseEntity<?> studentList(@RequestParam(name = "curriculumName", required = false) String curriculumName,
                                         @RequestParam(name = "curriculumTh", required = false) Long curriculumTh) {

        List<ManagerInquiryDto> managerInquiries;
        if (curriculumTh != null && curriculumName != null && !curriculumName.isEmpty()) {
            managerInquiries = managerInquiryService.getInquiryListWithCurriculumNameAndThFromStudents(curriculumName,curriculumTh);
        }
        else if (curriculumName != null && !curriculumName.isEmpty()) {
            managerInquiries = managerInquiryService.getInquiryListWithCurriculumNameFromStudents(curriculumName);
        }
        else {
            managerInquiries = managerInquiryService.getInquiryListDefaultFromStudents();
        }

        if (managerInquiries != null && !managerInquiries.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(managerInquiries);
        }
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    // 강사 문의 리스트 조회
    @GetMapping("/teachers-inquiries")
    public ResponseEntity<?> teacherList(@RequestParam(name = "curriculumName", required = false) String curriculumName,
                                         @RequestParam(name = "curriculumTh", required = false) Long curriculumTh) {

        List<ManagerInquiryDto> managerInquiries;
        if (curriculumTh != null && curriculumName != null && !curriculumName.isEmpty()) {
            managerInquiries = managerInquiryService.getInquiryListWithCurriculumNameAndThFromTeachers(curriculumName, curriculumTh);
        }
        else if (curriculumName != null && !curriculumName.isEmpty()) {
            managerInquiries = managerInquiryService.getInquiryListWithCurriculumNameFromTeachers(curriculumName);
        }
        else {
            managerInquiries = managerInquiryService.getInquiryListDefaultFromTeachers();
        }

        if (managerInquiries != null && !managerInquiries.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(managerInquiries);
        }
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    // 문의 상세 조회
    @GetMapping("/inquiries/{inquiryId}")
    public ResponseEntity<?> viewInquiry(@PathVariable("inquiryId") Long inquiryId) {
        ManagerInquiryDto result = managerInquiryService.getOneManagerInquiryDtoById(inquiryId);
        if (result != null) {
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    // 문의 답변
    @PostMapping("/inquiries/{inquiryId}/add-response")
    public ResponseEntity<?> addResponse(@Valid @RequestBody ManagerResponseDto managerResponseDto,
                                         @PathVariable("inquiryId") Long inquiryId) {
        boolean result = managerInquiryService.addResponse(managerResponseDto, inquiryId);
        if (result) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}