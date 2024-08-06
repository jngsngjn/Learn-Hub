package project.homelearn.controller.teacher.subject;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.homelearn.dto.teacher.subject.SubjectEnrollDto;
import project.homelearn.dto.teacher.subject.SubjectModifyDto;
import project.homelearn.service.teacher.TeacherSubjectService;

import java.security.Principal;

/**
 * Author : 정성진
 */
@Slf4j
@RestController
@RequestMapping("/teachers/subjects")
@RequiredArgsConstructor
public class TeacherSubjectController {

    private final TeacherSubjectService teacherSubjectService;

    // 과목 생성
    @PostMapping
    public ResponseEntity<?> createSubject(Principal principal,
                                           @Valid @ModelAttribute SubjectEnrollDto subjectDto) {
        teacherSubjectService.createSubject(principal.getName(), subjectDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 과목 수정
    @PatchMapping("/{subjectId}")
    public ResponseEntity<?> modifySubject(@PathVariable("subjectId") Long subjectId, Principal principal,
                                           @Valid @ModelAttribute SubjectModifyDto subjectDto) {
        boolean result = teacherSubjectService.modifySubject(subjectId, principal.getName(), subjectDto);
        if (result) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // 과목 삭제
    @DeleteMapping("/{subjectId}")
    public ResponseEntity<?> deleteSubject(@PathVariable("subjectId") Long subjectId, Principal principal) {
        boolean result = teacherSubjectService.deleteSubject(subjectId, principal.getName());
        if (result) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}