package project.homelearn.controller.student.question;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.homelearn.dto.student.board.CommentWriteDto;
import project.homelearn.dto.student.board.QuestionBoardWriteDto;
import project.homelearn.service.student.StudentQuestionBoardService;

import java.security.Principal;

/**
 * Author : 김승민
 * */
@Slf4j
@RestController
@RequestMapping("/students/question-boards")
@RequiredArgsConstructor
public class StudentQuestionBoardController {

    private final StudentQuestionBoardService studentQuestionBoardService;

    // 질문 등록
    @PostMapping
    public ResponseEntity<?> writeQuestionBoard(Principal principal,
                                                @Valid @RequestBody QuestionBoardWriteDto questionBoardWriteDto) {
        String username = principal.getName();
        studentQuestionBoardService.writeQuestionBoard(username, questionBoardWriteDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 질문 삭제
    @DeleteMapping("/{questionBoardId}")
    public ResponseEntity<?> deleteQuestionBoard(@PathVariable("questionBoardId") Long questionBoardId, Principal principal) {
        String username = principal.getName();
        boolean result = studentQuestionBoardService.deleteQuestionBoard(questionBoardId, username);

        if (result) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    // 질문 수정
    @PatchMapping("/{questionBoardId}")
    public ResponseEntity<?> modifyQuestionBoard(@PathVariable("questionBoardId") Long questionBoardId, Principal principal,
                                                 @Valid @ModelAttribute QuestionBoardWriteDto questionBoardWriteDto) {
        String username = principal.getName();

        boolean result = studentQuestionBoardService.modifyBoard(questionBoardId, username, questionBoardWriteDto);
        if (result) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    // 댓글 작성 (질문 답변)
    @PostMapping("/{questionBoardId}/comments")
    public ResponseEntity<?> writeComment(@PathVariable("questionBoardId") Long questionBoardId, Principal principal,
                                          @Valid @RequestBody CommentWriteDto commentDto) {
        String username = principal.getName();
        studentQuestionBoardService.writeComment(questionBoardId, username, commentDto);
        studentQuestionBoardService.incrementCommentCount(questionBoardId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 댓글 수정
    @PatchMapping("/{questionBoardId}/comments/{commentId}")
    public ResponseEntity<?> modifyComment(Principal principal,
                                           @PathVariable("commentId") Long commentId,
                                           @Valid @RequestBody CommentWriteDto commentDto) {
        String username = principal.getName();
        boolean result = studentQuestionBoardService.modifyComment(commentId, username, commentDto);
        if (result) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    // 댓글 삭제
    @DeleteMapping("/{questionBoardId}/comments/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable("questionBoardId") Long questionBoardId,
                                           @PathVariable("commentId") Long commentId,
                                           Principal principal) {
        String username = principal.getName();
        boolean result = studentQuestionBoardService.deleteComment(questionBoardId, commentId, username);

        if (result) {
            studentQuestionBoardService.decrementCommentCount(questionBoardId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    // 대댓글 작성
    @PostMapping("/{questionBoardId}/comments/{commentId}")
    public ResponseEntity<?> writeReplyComment(Principal principal,
                                               @PathVariable("commentId") Long commentId,
                                               @Valid @RequestBody CommentWriteDto commentDto,
                                               @PathVariable("questionBoardId")Long questionBoardId) {
        String username = principal.getName();
        studentQuestionBoardService.writeReplyComment(commentId, username, commentDto);
        studentQuestionBoardService.incrementCommentCount(questionBoardId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 대댓글 수정
    @PatchMapping("/{questionBoardId}/comments/{commentId}/replies/{replyId}")
    public ResponseEntity<?> modifyReplyComment(Principal principal,
                                                @PathVariable("commentId") Long replyId,
                                                @Valid @RequestBody CommentWriteDto commentDto) {
        String username = principal.getName();
        boolean result = studentQuestionBoardService.modifyReplyComment(replyId, username, commentDto);
        if (result) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    // 대댓글 삭제
    @DeleteMapping("/{questionBoardId}/comments/{commentId}/replies/{replyId}")
    public ResponseEntity<?> deleteReplyComment(@PathVariable("questionBoardId") Long questionBoardId,
                                                @PathVariable("replyId") Long replyId,
                                                Principal principal) {
        String username = principal.getName();
        boolean result = studentQuestionBoardService.deleteComment(questionBoardId, replyId, username);

        if (result) {
            studentQuestionBoardService.decrementCommentCount(questionBoardId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    // 질문 게시판 글 스크랩 = 나도 궁금해
    @PostMapping("/{questionBoardId}/scrap")
    public ResponseEntity<?> addScrap(@PathVariable("questionBoardId")Long questionBoardId,
                                      Principal principal) {
        String username = principal.getName();
        boolean result = studentQuestionBoardService.addScrap(username, questionBoardId);

        if (result) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{questionBoardId}/scrap")
    public ResponseEntity<?> deleteScrap(@PathVariable("questionBoardId")Long questionBoardId,
                                         Principal principal){
        String username = principal.getName();
        boolean result = studentQuestionBoardService.deleteScrap(username,questionBoardId);

        if (result) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}