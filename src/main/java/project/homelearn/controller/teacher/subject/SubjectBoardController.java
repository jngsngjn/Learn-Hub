package project.homelearn.controller.teacher.subject;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.homelearn.service.teacher.SubjectService;

/**
 * Author : 정성진
 */
@Slf4j
@RestController
@RequestMapping("/teachers/subjects/boards")
@RequiredArgsConstructor
public class SubjectBoardController {

    private final SubjectService subjectService;

    // 글 등록
    @PostMapping
    public ResponseEntity<?> writeBoard() {
        return null;
    }

    // 글 수정

    // 글 삭제

    // 조회수 증가
}