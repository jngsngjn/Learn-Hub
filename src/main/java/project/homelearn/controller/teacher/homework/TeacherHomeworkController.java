package project.homelearn.controller.teacher.homework;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.homelearn.dto.teacher.homework.HomeworkEnrollDto;
import project.homelearn.dto.teacher.homework.HomeworkFeedbackDto;
import project.homelearn.dto.teacher.homework.HomeworkModifyDto;
import project.homelearn.service.teacher.homework.TeacherHomeworkService;

import java.security.Principal;

/**
 * Author : 정성진
 */
@Slf4j
@RestController
@RequestMapping("/teachers/homeworks")
@RequiredArgsConstructor
public class TeacherHomeworkController {

    private final TeacherHomeworkService homeworkService;

    // 과제 등록
    @PostMapping
    public ResponseEntity<?> enrollHomework(Principal principal,
                                            @Valid @ModelAttribute HomeworkEnrollDto homeworkDto) {
        homeworkService.enrollHomework(principal.getName(), homeworkDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 과제 수정
    @PatchMapping("/{homeworkId}")
    public ResponseEntity<?> modifyHomework(Principal principal,
                                            @PathVariable("homeworkId") Long homeworkId,
                                            @Valid @ModelAttribute HomeworkModifyDto homeworkDto) {
        boolean result = homeworkService.modifyHomework(homeworkId, principal.getName(), homeworkDto);
        if (result) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    // 과제 삭제
    @DeleteMapping("/{homeworkId}")
    public ResponseEntity<?> deleteHomework(Principal principal,
                                            @PathVariable("homeworkId") Long homeworkId) {
        boolean result = homeworkService.deleteHomework(homeworkId, principal.getName());
        if (result) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    // 피드백 등록 *테스트 해야 함
    @PostMapping("/{homeworkId}/students/{studentHomeworkId}")
    public ResponseEntity<?> feedbackHomework(Principal principal,
                                              @PathVariable("homeworkId") Long homeworkId,
                                              @PathVariable("studentHomeworkId") Long studentHomeworkId,
                                              @Valid @RequestBody HomeworkFeedbackDto homeworkDto) {
        boolean result = homeworkService.feedbackHomework(homeworkId, studentHomeworkId, principal.getName(), homeworkDto);
        if (result) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    // 피드백 수정 *테스트 해야 함
    @PatchMapping("/{homeworkId}/students/{studentHomeworkId}")
    public ResponseEntity<?> modifyFeedbackHomework(Principal principal,
                                              @PathVariable("homeworkId") Long homeworkId,
                                              @PathVariable("studentHomeworkId") Long studentHomeworkId,
                                              @Valid @RequestBody HomeworkFeedbackDto homeworkDto) {
        boolean result = homeworkService.modifyFeedbackHomework(homeworkId, studentHomeworkId, principal.getName(), homeworkDto);
        if (result) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    // 피드백 삭제 *테스트 해야 함
    @DeleteMapping("/{homeworkId}/students/{studentHomeworkId}")
    public ResponseEntity<?> deleteFeedbackHomework(Principal principal,
                                              @PathVariable("homeworkId") Long homeworkId,
                                              @PathVariable("studentHomeworkId") Long studentHomeworkId) {
        boolean result = homeworkService.deleteFeedbackHomework(homeworkId, studentHomeworkId, principal.getName());
        if (result) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}