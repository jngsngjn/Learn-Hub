package project.homelearn.controller.teacher.homework;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.homelearn.dto.teacher.homework.HomeworkEnrollDto;
import project.homelearn.service.teacher.TeacherHomeworkService;

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

    @PostMapping
    public ResponseEntity<?> enrollHomework(Principal principal,
                                            @Valid @ModelAttribute HomeworkEnrollDto homeworkDto) {
        homeworkService.enrollHomework(principal.getName(), homeworkDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}