package project.homelearn.controller.student.freeboard;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.homelearn.dto.student.board.CommentWriteDto;
import project.homelearn.dto.student.board.FreeBoardWriteDto;
import project.homelearn.service.student.StudentBoardService;

import java.security.Principal;

/**
 * Author : 정성진
 */
@Slf4j
@RestController
@RequestMapping("/students/boards")
@RequiredArgsConstructor
public class StudentBoardController {

    private final StudentBoardService boardService;

    // 글 등록
    @PostMapping
    public ResponseEntity<?> writeBoard(Principal principal,
                                        @Valid @ModelAttribute FreeBoardWriteDto boardDto) {
        String username = principal.getName();

        boardService.writeBoard(username, boardDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 글 수정
    @PatchMapping("/{boardId}")
    public ResponseEntity<?> modifyBoard(@PathVariable("boardId") Long boardId, Principal principal,
                                         @Valid @ModelAttribute FreeBoardWriteDto boardDto) {
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
    @PostMapping("/{boardId}/comments")
    public ResponseEntity<?> writeComment(@PathVariable("boardId") Long boardId, Principal principal,
                                          @Valid @RequestBody CommentWriteDto commentDto) {
        String username = principal.getName();
        boardService.writeComment(boardId, username, commentDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 댓글 수정
    @PatchMapping("/{boardId}/comments/{commentId}")
    public ResponseEntity<?> modifyComment(Principal principal,
                                           @PathVariable("commentId") Long commentId,
                                           @Valid @RequestBody CommentWriteDto commentDto) {
        String username = principal.getName();
        boolean result = boardService.modifyComment(commentId, username, commentDto);
        if (result) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    // 댓글 삭제
    @DeleteMapping("/{boardId}/comments/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable("boardId") Long boardId,
                                           @PathVariable("commentId") Long commentId,
                                           Principal principal) {
        String username = principal.getName();
        boolean result = boardService.deleteComment(boardId, commentId, username);

        if (result) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    // 대댓글 등록
    @PostMapping("/{boardId}/comments/{commentId}")
    public ResponseEntity<?> writeReplyComment(Principal principal,
                                                 @PathVariable("commentId") Long commentId,
                                                 @Valid @RequestBody CommentWriteDto commentDto) {
        String username = principal.getName();
        boardService.writeReplyComment(commentId, username, commentDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 대댓글 수정
    @PatchMapping("/{boardId}/comments/{commentId}/replies/{replyId}")
    public ResponseEntity<?> modifyReplyComment(Principal principal,
                                                  @PathVariable("commentId") Long replyId,
                                                  @Valid @RequestBody CommentWriteDto commentDto) {
        String username = principal.getName();
        boolean result = boardService.modifyReplyComment(replyId, username, commentDto);
        if (result) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    // 대댓글 삭제
    @DeleteMapping("/{boardId}/comments/{commentId}/replies/{replyId}")
    public ResponseEntity<?> deleteReplyComment(@PathVariable("boardId") Long boardId,
                                                @PathVariable("replyId") Long replyId,
                                                Principal principal) {
        String username = principal.getName();
        boolean result = boardService.deleteComment(boardId, replyId, username);

        if (result) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}