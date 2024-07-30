package project.homelearn.controller.teacher.subject;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.homelearn.dto.teacher.subject.SubjectCreateDto;
import project.homelearn.service.teacher.SubjectService;

import java.security.Principal;

/**
 * Author : 정성진
 */
@Slf4j
@RestController
@RequestMapping("/teachers/subjects")
@RequiredArgsConstructor
public class SubjectController {

    private final SubjectService subjectService;

    // 과목 생성
    @PostMapping
    public ResponseEntity<?> createSubject(Principal principal,
                                           @Valid @ModelAttribute SubjectCreateDto subjectDto) {
        subjectService.createSubject(principal.getName(), subjectDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}