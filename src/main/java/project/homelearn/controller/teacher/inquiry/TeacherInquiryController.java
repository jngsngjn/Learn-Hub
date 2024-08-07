package project.homelearn.controller.teacher.inquiry;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.homelearn.dto.common.inquiry.InquiryWriteDto;
import project.homelearn.dto.teacher.inquiry.TeacherInquiryDto;
import project.homelearn.dto.teacher.inquiry.TeacherResponseDto;
import project.homelearn.service.teacher.TeacherInquiryService;

import java.security.Principal;
import java.util.List;

/**
 * Author : 김승민
 */
@Slf4j
@RestController
@RequestMapping("/teachers")
@RequiredArgsConstructor
public class TeacherInquiryController {

    private final TeacherInquiryService teacherInquiryService;

    // 1:1 문의 작성 : 강사 -> 매니저
    @PostMapping("/send-inquiries")
    public ResponseEntity<?> sendInquiry(Principal principal,
                                         @Valid @RequestBody InquiryWriteDto writeDto) {
        String username = principal.getName();
        boolean result = teacherInquiryService.writeInquiryToManager(username, writeDto);

        if (result) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    // 학생 문의 내역 조회
    @GetMapping("/student-inquiries")
    public ResponseEntity<?> studentList() {

        List<TeacherInquiryDto> teacherInquiries  = teacherInquiryService.getInquiryListDefaultFromStudents();

        if (teacherInquiries != null && !teacherInquiries.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(teacherInquiries);
        }
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    // 학생 문의 내역 상세 조회
    @GetMapping("/inquiries/{inquiryId}")
    public ResponseEntity<?> viewInquiry(@PathVariable("inquiryId") Long inquiryId) {
        TeacherInquiryDto result = teacherInquiryService.getOneManagerInquiryDtoById(inquiryId);
        if (result != null) {
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    // 학생 문의에 답변
    @PostMapping("/inquiries/{inquiryId}/add-response")
    public ResponseEntity<?> addResponseToStudent(@Valid @RequestBody TeacherResponseDto teacherResponseDto,
                                                  @PathVariable("inquiryId") Long inquiryId) {
        boolean result = teacherInquiryService.addResponseToStudent(teacherResponseDto, inquiryId);
        if (result) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}