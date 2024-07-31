package project.homelearn.controller.teacher.board;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.homelearn.dto.student.board.CommentWriteDto;
import project.homelearn.service.teacher.TeacherQuestionBoardService;

import java.security.Principal;

@Slf4j
@RestController
@RequestMapping("/teachers/questionBoards")
@RequiredArgsConstructor
public class TeacherQuestionBoardController {

    private final TeacherQuestionBoardService teacherQuestionBoardService;

    //댓글 작성
    @PostMapping("/{questionBoardId}/comments")
    public ResponseEntity<?> writeComment(@PathVariable("questionBoardId") Long questionBoardId, Principal principal,
                                          @Valid @RequestBody CommentWriteDto commentDto) {
        String username = principal.getName();
        teacherQuestionBoardService.writeComment(questionBoardId, username, commentDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //댓글 수정
    @PatchMapping("/{questionBoardId}/comments/{commentId}")
    public ResponseEntity<?> modifyComment(Principal principal,
                                           @PathVariable("commentId") Long commentId,
                                           @Valid @RequestBody CommentWriteDto commentDto) {
        String username = principal.getName();
        boolean result = teacherQuestionBoardService.modifyComment(commentId, username, commentDto);
        if (result) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    //댓글 삭제
    @DeleteMapping("/{questionBoardId}/comments/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable("questionBoardId") Long questionBoardId,
                                           @PathVariable("commentId") Long commentId,
                                           Principal principal) {
        String username = principal.getName();
        boolean result = teacherQuestionBoardService.deleteComment(questionBoardId, commentId, username);

        if (result) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    //대댓글 작성
    @PostMapping("/{questionBoardId}/comments/{commentId}")
    public ResponseEntity<?> writeReplyComment(Principal principal,
                                               @PathVariable("commentId") Long commentId,
                                               @Valid @RequestBody CommentWriteDto commentDto) {
        String username = principal.getName();
        teacherQuestionBoardService.writeReplyComment(commentId, username, commentDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //대댓글 수정
    @PatchMapping("/{questionBoardId}/comments/{commentId}/replies/{replyId}")
    public ResponseEntity<?> modifyReplyComment(Principal principal,
                                                @PathVariable("commentId") Long replyId,
                                                @Valid @RequestBody CommentWriteDto commentDto) {
        String username = principal.getName();
        boolean result = teacherQuestionBoardService.modifyReplyComment(replyId, username, commentDto);
        if (result) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    //대댓글 삭제
    @DeleteMapping("/{questionBoardId}/comments/{commentId}/replies/{replyId}")
    public ResponseEntity<?> deleteReplyComment(@PathVariable("questionBoardId") Long boardId,
                                                @PathVariable("replyId") Long replyId,
                                                Principal principal) {
        String username = principal.getName();
        boolean result = teacherQuestionBoardService.deleteComment(boardId, replyId, username);

        if (result) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    //조회수 증가

    //글 상세보기

    //게시글 리스트
}
