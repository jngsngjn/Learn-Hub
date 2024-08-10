package project.homelearn.controller.student.inquiry;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.homelearn.dto.student.inquiry.StudentInquiryDto;
import project.homelearn.service.student.StudentInquiryService;

import java.security.Principal;

/**
 * Author : 정성진
 */
@Slf4j
@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentInquiryController {

    private final StudentInquiryService inquiryService;

    // 문의 등록 : 학생 -> 매니저
    @PostMapping("/managers-inquiries")
    public ResponseEntity<?> inquiryToManager(Principal principal,
                                              @Valid @RequestBody StudentInquiryDto inquiryDto) {
        String username = principal.getName();

        inquiryService.inquiryToManger(username, inquiryDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 문의 수정 : 학생 -> 매니저
    @PatchMapping("/managers-inquiries/{inquiryId}")
    public ResponseEntity<?> modifyManagerInquiry(@PathVariable("inquiryId") Long inquiryId, Principal principal,
                                                  @Valid @RequestBody StudentInquiryDto inquiryDto) {
        String username = principal.getName();
        boolean result = inquiryService.modifyManagerInquiry(inquiryId, username, inquiryDto);

        if (result) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    // 문의 삭제 : 학생 -> 매니저
    @DeleteMapping("/managers-inquiries/{inquiryId}")
    public ResponseEntity<?> deleteManagerInquiry(@PathVariable("inquiryId") Long inquiryId, Principal principal) {
        String username = principal.getName();
        boolean result = inquiryService.deleteManagerInquiry(inquiryId, username);

        if (result) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    // 문의 등록 : 학생 -> 강사
    @PostMapping("/teachers-inquiries")
    public ResponseEntity<?> inquiryToTeacher(Principal principal,
                                              @Valid @RequestBody StudentInquiryDto inquiryDto) {
        String username = principal.getName();

        inquiryService.inquiryToTeacher(username, inquiryDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 문의 수정 : 학생 -> 강사
    @PatchMapping("/teachers-inquiries/{inquiryId}")
    public ResponseEntity<?> modifyTeacherInquiry(@PathVariable("inquiryId") Long inquiryId, Principal principal,
                                                  @Valid @RequestBody StudentInquiryDto inquiryDto) {
        String username = principal.getName();
        boolean result = inquiryService.modifyTeacherInquiry(inquiryId, username, inquiryDto);

        if (result) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    // 문의 삭제 : 학생 -> 강사
    @DeleteMapping("/teachers-inquiries/{inquiryId}")
    public ResponseEntity<?> deleteTeacherInquiry(@PathVariable("inquiryId") Long inquiryId, Principal principal) {
        String username = principal.getName();
        boolean result = inquiryService.deleteTeacherInquiry(inquiryId, username);

        if (result) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}