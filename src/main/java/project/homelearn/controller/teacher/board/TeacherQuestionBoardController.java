package project.homelearn.controller.teacher.board;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.homelearn.dto.common.board.QuestionBoardCommentDto;
import project.homelearn.dto.common.board.QuestionBoardDetailDto;
import project.homelearn.dto.common.board.QuestionBoardDto;
import project.homelearn.dto.student.board.CommentWriteDto;
import project.homelearn.entity.curriculum.Curriculum;
import project.homelearn.repository.curriculum.CurriculumRepository;
import project.homelearn.service.teacher.TeacherQuestionBoardService;

import java.security.Principal;
import java.util.List;

/**
 * Author : 김승민
 */
@Slf4j
@RestController
@RequestMapping("/teachers/question-boards")
@RequiredArgsConstructor
public class TeacherQuestionBoardController {

    private final CurriculumRepository curriculumRepository;
    private final TeacherQuestionBoardService teacherQuestionBoardService;

    // 게시글 리스트
    @GetMapping
    public ResponseEntity<?> getQuestionBoardList(@RequestParam(required = false) String filterType,
                                                  @RequestParam(required = false) String subjectName,
                                                  Principal principal,
                                                  @RequestParam(name = "page", defaultValue = "0") int page) {
        int size = 15;

        String username = principal.getName();
        Curriculum curriculum = curriculumRepository.findCurriculumByUsername(username);

        if (curriculum == null) {
            return new ResponseEntity<>("해당 교사의 커리큘럼을 찾을 수 없습니다.", HttpStatus.NOT_FOUND);
        }

        Pageable pageable = PageRequest.of(page, size);
        Page<QuestionBoardDto> questionBoardList = teacherQuestionBoardService.getQuestionBoardList(filterType, subjectName, curriculum, pageable);

        if (questionBoardList.getTotalElements() > 0) {
            return new ResponseEntity<>(questionBoardList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("등록된 질문이 없습니다.", HttpStatus.NOT_FOUND);
        }
    }

    // 댓글 작성
    @PostMapping("/{questionBoardId}/comments")
    public ResponseEntity<?> writeComment(@PathVariable("questionBoardId") Long questionBoardId, Principal principal,
                                          @Valid @RequestBody CommentWriteDto commentDto) {
        String username = principal.getName();
        teacherQuestionBoardService.writeComment(questionBoardId, username, commentDto);
        teacherQuestionBoardService.incrementCommentCount(questionBoardId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 댓글 수정
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
            teacherQuestionBoardService.decrementCommentCount(questionBoardId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    // 대댓글 작성
    @PostMapping("/{questionBoardId}/comments/{commentId}")
    public ResponseEntity<?> writeReplyComment(Principal principal,
                                               @PathVariable("commentId") Long commentId,
                                               @Valid @RequestBody CommentWriteDto commentDto,
                                               @PathVariable("questionBoardId") Long questionBoardId) {
        String username = principal.getName();
        teacherQuestionBoardService.writeReplyComment(commentId, username, commentDto);
        teacherQuestionBoardService.incrementCommentCount(questionBoardId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 대댓글 수정
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

    // 대댓글 삭제
    @DeleteMapping("/{questionBoardId}/comments/{commentId}/replies/{replyId}")
    public ResponseEntity<?> deleteReplyComment(@PathVariable("questionBoardId") Long questionBoardId,
                                                @PathVariable("replyId") Long replyId,
                                                Principal principal) {
        String username = principal.getName();
        boolean result = teacherQuestionBoardService.deleteComment(questionBoardId, replyId, username);

        if (result) {
            teacherQuestionBoardService.decrementCommentCount(questionBoardId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    // 글 상세보기
    @GetMapping("/{questionBoardId}")
    public ResponseEntity<?> viewQuestionBoard(@PathVariable("questionBoardId") Long questionBoardId) {
        QuestionBoardDetailDto viewQuestionBoard = teacherQuestionBoardService.getQuestionBoard(questionBoardId);

        if (viewQuestionBoard != null) {
            // 조회수 증가
            teacherQuestionBoardService.incrementViewCount(questionBoardId);
            return new ResponseEntity<>(viewQuestionBoard, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // 해당 글 상세보기 페이지 댓글 모음
    @GetMapping("/{questionBoardId}/comments")
    public ResponseEntity<?> viewComments(@PathVariable("questionBoardId") Long questionBoardId) {
        List<QuestionBoardCommentDto> viewComments = teacherQuestionBoardService.getQuestionBoardComment(questionBoardId);

        if (viewComments != null) {
            return new ResponseEntity<>(viewComments, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("작성된 댓글이 없습니다.",HttpStatus.NOT_FOUND);
        }
    }
}