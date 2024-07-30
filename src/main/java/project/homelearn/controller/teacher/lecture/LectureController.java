package project.homelearn.controller.teacher.lecture;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.homelearn.dto.teacher.lecture.LectureEnrollDto;
import project.homelearn.service.teacher.LectureService;

import java.security.Principal;

/**
 * Author : 정성진
 */
@Slf4j
@RestController
@RequestMapping("/teachers/lectures")
@RequiredArgsConstructor
public class LectureController {

    private final LectureService lectureService;

    // 강의 등록
    @PostMapping
    public ResponseEntity<?> enrollLecture(Principal principal,
                                           @Valid @RequestBody LectureEnrollDto lectureDto) {
        String username = principal.getName();
        lectureService.enrollLecture(username, lectureDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 강의 수정
    @PatchMapping("/{lectureId}")
    public ResponseEntity<?> modifyLecture(@PathVariable("lectureId") Long lectureId,
                                           @Valid @RequestBody LectureEnrollDto lectureDto) {
        lectureService.modifyLecture(lectureId, lectureDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 강의 삭제
    @PatchMapping("/{lectureId}")
    public ResponseEntity<?> deleteLecture(@PathVariable("lectureId") Long lectureId) {
        lectureService.deleteLecture(lectureId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}