package project.homelearn.controller.student.freeboard;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.homelearn.dto.student.board.FreeBoardCommentDto;
import project.homelearn.dto.student.board.FreeBoardDetailDto;
import project.homelearn.dto.student.board.FreeBoardDto;
import project.homelearn.entity.curriculum.Curriculum;
import project.homelearn.repository.curriculum.CurriculumRepository;
import project.homelearn.service.student.StudentBoardService;

import java.security.Principal;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/students/boards")
@RequiredArgsConstructor
public class StudentBoardViewController {

    private final StudentBoardService boardService;
    private final CurriculumRepository curriculumRepository;

    // 전체 리스트
    @GetMapping
    public ResponseEntity<?> viewBoardList(Principal principal,
                                           @RequestParam(name = "page", defaultValue = "0") int page) {
        int size = 15;

        String username = principal.getName();
        Curriculum myCurriculum = curriculumRepository.findCurriculumByUsername(username);

        if (myCurriculum == null) {
            return new ResponseEntity<>("해당 학생의 커리큘럼을 찾을 수 없습니다.", HttpStatus.NOT_FOUND);
        }

        Pageable pageable = PageRequest.of(page, size);

        Page<FreeBoardDto> boardList = boardService.getBoardList(myCurriculum,pageable);

        if(boardList.getTotalElements() > 0) {
            return new ResponseEntity<>(boardList, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // 상세보기
    @GetMapping("/{boardId}")
    public ResponseEntity<?> viewBoard(@PathVariable Long boardId) {
        FreeBoardDetailDto viewBoard = boardService.getBoard(boardId);

        if (viewBoard != null) {
            boardService.incrementViewCount(boardId);
            return new ResponseEntity<>(viewBoard, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // 댓글 추출
    @GetMapping("/{boardId}/comments")
    public ResponseEntity<?> viewComments(@PathVariable("boardId")Long boardId) {
        List<FreeBoardCommentDto> viewComments = boardService.getBoardComment(boardId);

        if (viewComments != null) {
            return new ResponseEntity<>(viewComments, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
