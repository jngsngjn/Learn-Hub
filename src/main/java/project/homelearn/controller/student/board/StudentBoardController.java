package project.homelearn.controller.student.board;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.homelearn.dto.student.board.StudentBoardWriteDto;
import project.homelearn.service.student.StudentBoardService;

import java.security.Principal;

/**
 * Author : 정성진
 */
@Slf4j
@RestController
@RequestMapping("/students/board")
@RequiredArgsConstructor
public class StudentBoardController {

    private final StudentBoardService boardService;

    // 글 등록
    @PostMapping
    public ResponseEntity<?> writeBoard(Principal principal,
                                        @Valid @RequestBody StudentBoardWriteDto boardDto) {
        String username = principal.getName();

        boardService.writeBoard(username, boardDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 글 수정
    @PatchMapping("/{boardId}")
    public ResponseEntity<?> modifyBoard(@PathVariable("boardId") Long boardId, Principal principal,
                                         @Valid @RequestBody StudentBoardWriteDto boardDto) {
        String username = principal.getName();
        boolean result = boardService.modifyBoard(boardId, username, boardDto);

        if (result) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    // 글 삭제
    @DeleteMapping("/{boardId}")
    public ResponseEntity<?> deleteBoard(@PathVariable("boardId") Long boardId, Principal principal) {
        String username = principal.getName();
        boolean result = boardService.deleteBoard(boardId, username);

        if (result) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    // 댓글 등록

    // 댓글 수정

    // 댓글 삭제
}