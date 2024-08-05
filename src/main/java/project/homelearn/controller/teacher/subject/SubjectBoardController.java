package project.homelearn.controller.teacher.subject;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.homelearn.dto.teacher.subject.SubjectBoardWriteDto;
import project.homelearn.service.teacher.TeacherSubjectService;

import java.security.Principal;

/**
 * Author : 정성진
 */
@Slf4j
@RestController
@RequestMapping("/teachers/subjects/{subjectId}/boards")
@RequiredArgsConstructor
public class SubjectBoardController {

    private final TeacherSubjectService teacherSubjectService;

    // 글 등록
    @PostMapping
    public ResponseEntity<?> writeBoard(@PathVariable("subjectId") Long subjectId,
                                        @Valid @ModelAttribute SubjectBoardWriteDto boardDto,
                                        Principal principal) {
        teacherSubjectService.writeBoard(subjectId, boardDto, principal.getName());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 글 수정

    // 글 삭제

    // 조회수 증가
}