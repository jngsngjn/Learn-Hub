package project.homelearn.controller.student.homework;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.homelearn.dto.student.homework.HomeworkSubmitDto;
import project.homelearn.dto.student.homework.HomeworkUpdateDto;
import project.homelearn.service.student.StudentHomeworkService;

import java.security.Principal;

/**
 * Author : 동재완
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/students/homeworks")
public class StudentHomeWorkController {

    private final StudentHomeworkService studentHomeworkService;

    // 학생 과제 등록
    @PostMapping
    public ResponseEntity<?> submitHomework(Principal principal,
                                            @Valid @ModelAttribute HomeworkSubmitDto homeWorkSubmitDto) {
        String username = principal.getName();
        boolean result = studentHomeworkService.submitHomework(username, homeWorkSubmitDto);
        if (result) {
            return new ResponseEntity<>(HttpStatus.OK); // 제출 성공
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // 제출 실패
        }
    }

    // 학생 과제 수정
    @PatchMapping("/{homeworkId}")
    public ResponseEntity<?> updateHomework(Principal principal,
                                            @PathVariable("homeworkId") Long homeworkId,
                                            @Valid @ModelAttribute HomeworkUpdateDto homeWorkUpdateDto) {
        boolean result = studentHomeworkService.updateHomework(homeworkId, principal.getName(), homeWorkUpdateDto);
        if (result) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // 학생 과제 삭제
    @DeleteMapping("/{homeworkId}")
    public ResponseEntity<?> deleteHomework(@PathVariable("homeworkId") Long homeworkId) {
        boolean result = studentHomeworkService.deleteHomework(homeworkId);
        if (result) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}