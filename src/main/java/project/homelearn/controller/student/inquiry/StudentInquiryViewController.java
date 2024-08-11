package project.homelearn.controller.student.inquiry;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.homelearn.dto.student.inquiry.StudentInquiryDetailDto;
import project.homelearn.dto.student.inquiry.StudentInquiryListDto;
import project.homelearn.service.student.StudentInquiryService;

import java.security.Principal;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentInquiryViewController {

    private final StudentInquiryService inquiryService;

    @GetMapping("/managers-inquiries")
    public ResponseEntity<?> viewManagerInquiry(Principal principal) {
        String username = principal.getName();

        List<StudentInquiryListDto> myInquiryList = inquiryService.getMyManagerInquiryList(username);

        if (myInquiryList != null) {
            return new ResponseEntity<>(myInquiryList, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // 상세보기 : 학생 -> 매니저
    @GetMapping("/managers-inquiries/{inquiryId}")
    public ResponseEntity<?> viewManagerInquiryDetail(@PathVariable("inquiryId") Long inquiryId) {
        StudentInquiryDetailDto myInquiry = inquiryService.getMyManagerInquiryDetail(inquiryId);

        if (myInquiry != null) {
            return new ResponseEntity<>(myInquiry, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/teachers-inquiries")
    public ResponseEntity<?> viewTeacherInquiry(Principal principal) {
        String username = principal.getName();

        List<StudentInquiryListDto> myInquiryList = inquiryService.getMyTeacherInquiryList(username);

        if (myInquiryList != null) {
            return new ResponseEntity<>(myInquiryList, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // 상세보기 : 학생 -> 강사
    @GetMapping("/teachers-inquiries/{inquiryId}")
    public ResponseEntity<?> viewTeacherInquiryDetail(@PathVariable("inquiryId") Long inquiryId) {
        StudentInquiryDetailDto myInquiry = inquiryService.getMyTeacherInquiryDetail(inquiryId);

        if (myInquiry != null) {
            return new ResponseEntity<>(myInquiry, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}