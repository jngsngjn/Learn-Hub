package project.homelearn.controller.teacher.inquiry;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.homelearn.dto.teacher.inquiry.TeacherInquiryDto;
import project.homelearn.dto.teacher.inquiry.TeacherInquiryListToManagerDto;
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
public class TeacherInquiryViewController {

    private final TeacherInquiryService teacherInquiryService;

    // 학생 문의 내역 조회
    @GetMapping("/students-inquiries")
    public ResponseEntity<?> viewStudentList() {

        List<TeacherInquiryDto> teacherInquiries  = teacherInquiryService.getInquiryListDefaultFromStudents();

        if (teacherInquiries != null && !teacherInquiries.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(teacherInquiries);
        }
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    // 학생 문의 내역 상세 조회
    @GetMapping("/students-inquiries/{inquiryId}")
    public ResponseEntity<?> viewInquiry(@PathVariable("inquiryId") Long inquiryId) {
        TeacherInquiryDto result = teacherInquiryService.getOneStudentInquiryDtoById(inquiryId);
        if (result != null) {
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    // 매니저에게 문의한 내역 조회
    @GetMapping("/managers-inquiries")
    public ResponseEntity<?> viewManagerInquiry(Principal principal) {
        String username = principal.getName();

        List<TeacherInquiryListToManagerDto> myInquiryList = teacherInquiryService.getMyManagerInquiryList(username);

        if (myInquiryList != null) {
            return new ResponseEntity<>(myInquiryList, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // 매니저에게 문의한 내역 상세보기
    @GetMapping("/managers-inquiries/{inquiryId}")
    public ResponseEntity<?> viewManagerInquiryDetail(@PathVariable("inquiryId")Long inquiryId) {
        TeacherInquiryDto result = teacherInquiryService.getOneManagerInquiryDtoById(inquiryId);

        if (result != null) {
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}