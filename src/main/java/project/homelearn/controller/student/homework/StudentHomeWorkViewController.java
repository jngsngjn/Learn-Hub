package project.homelearn.controller.student.homework;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.homelearn.dto.student.homework.HomeworkListDto;
import project.homelearn.dto.student.homework.MySubmitDetailDto;
import project.homelearn.dto.student.homework.StudentHomeworkDetailDto;
import project.homelearn.service.student.StudentHomeworkService;

import java.security.Principal;

/**
 * Author : 김승민
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/students/homeworks")
public class StudentHomeWorkViewController {

    private final StudentHomeworkService studentHomeworkService;

    /*
    [학생] 과제 페이지 조회
    1. 진행 중인 과제
    2. 마감된 과제
    */
    @GetMapping("/progress")
    public ResponseEntity<?> viewProceedingHomework(Principal principal,
                                                    @RequestParam(name = "page", defaultValue = "0") int page) {
        int size = 1;

        String username = principal.getName();
        Pageable pageable = PageRequest.of(page, size);
        Page<HomeworkListDto> result = studentHomeworkService.getHomeworkProceeding(username, pageable);

        if (result != null) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("진행중인 과제가 없습니다.",HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/closed")
    public ResponseEntity<?> viewClosedHomework(Principal principal,
                                                @RequestParam(name = "page", defaultValue = "0") int page) {
        int size = 3;

        String username = principal.getName();
        Pageable pageable = PageRequest.of(page, size);
        Page<HomeworkListDto> result = studentHomeworkService.getHomeworkClosed(username, pageable);

        if (result != null) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("마감된 과제가 없습니다.",HttpStatus.NOT_FOUND);
        }
    }

    /*
    과제 상세 조회
    1. 과제 내용 조회
    2. 본인 제출 내역 조회
    */
    @GetMapping("/{homeworkId}")
    public ResponseEntity<?> viewHomeworkDetail(@PathVariable("homeworkId") Long homeworkId) {
        StudentHomeworkDetailDto homeworkDetail = studentHomeworkService.getHomeworkDetail(homeworkId);

        if (homeworkDetail != null) {
            return new ResponseEntity<>(homeworkDetail, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("해당 과제가 없습니다.",HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{homeworkId}/my-submit")
    public ResponseEntity<?> viewHomeworkMySubmit(@PathVariable("homeworkId") Long homeworkId) {
        MySubmitDetailDto mySubmit = studentHomeworkService.getMySubmit(homeworkId);

        if (mySubmit != null) {
            return new ResponseEntity<>(mySubmit, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("제출한 내역이 없습니다.",HttpStatus.NOT_FOUND);
        }
    }
}