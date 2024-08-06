package project.homelearn.controller.student.homework;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.homelearn.dto.student.homework.HomeWorkCreateDto;
import project.homelearn.dto.student.homework.HomeWorkUpdateDto;
import project.homelearn.service.student.StudentHomeworkService;

import java.security.Principal;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/students")
public class StudentHomeWorkController {

    private final StudentHomeworkService studentHomeworkService;

    // 학생 과제 등록
    @PostMapping()
    public ResponseEntity<?> enrollmentHomework(Principal principal,
                                                @Valid @ModelAttribute HomeWorkCreateDto homeWorkCreateDto) {
        String username = principal.getName();
        // 로그 추가
        log.debug("Submitting homework for username: {}", username);

        boolean result = studentHomeworkService.createHomework(username, homeWorkCreateDto);

        if (result) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // 학생 과제 수정
    @PatchMapping("/{id}")
    public ResponseEntity<?> updateHomework(@PathVariable("id") Long id,
                                            @Valid @ModelAttribute HomeWorkUpdateDto homeWorkUpdateDto) {
        boolean result = studentHomeworkService.updateHomework(id, homeWorkUpdateDto);
        if (result) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // 학생 과제 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteHomework(@PathVariable("id") Long id) {
        boolean result = studentHomeworkService.deleteHomework(id);

        if (result) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
