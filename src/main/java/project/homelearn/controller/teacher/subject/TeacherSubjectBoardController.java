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
public class TeacherSubjectBoardController {

    private final TeacherSubjectService teacherSubjectService;

    // 글 등록
    @PostMapping
    public ResponseEntity<?> writeSubjectBoard(@PathVariable("subjectId") Long subjectId,
                                        @Valid @ModelAttribute SubjectBoardWriteDto boardDto,
                                        Principal principal) {
        teacherSubjectService.writeBoard(subjectId, boardDto, principal.getName());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 글 수정
    @PatchMapping("/{boardId}")
    public ResponseEntity<?> modifySubjectBoard(@PathVariable("subjectId") Long subjectId,
                                                @PathVariable("boardId") Long boardId,
                                         @Valid @ModelAttribute SubjectBoardWriteDto boardDto,
                                         Principal principal) {
        boolean result = teacherSubjectService.modifySubjectBoard(subjectId, boardId, boardDto, principal.getName());
        if (result) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    // 글 삭제
    @DeleteMapping("/{boardId}")
    public ResponseEntity<?> deleteSubjectBoard(@PathVariable("subjectId") Long subjectId,
                                                @PathVariable("boardId") Long boardId,
                                                Principal principal) {
        boolean result = teacherSubjectService.deleteSubjectBoard(subjectId, boardId, principal.getName());
        if (result) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}