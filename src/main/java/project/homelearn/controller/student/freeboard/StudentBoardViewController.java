package project.homelearn.controller.student.freeboard;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.homelearn.dto.student.board.FreeBoardDto;
import project.homelearn.entity.curriculum.Curriculum;
import project.homelearn.repository.curriculum.CurriculumRepository;
import project.homelearn.service.student.StudentBoardService;

import java.security.Principal;

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
                                           @RequestParam(name = "page", defaultValue = "0") int page){
        int size = 15;

        String username = principal.getName();
        Curriculum myCurriculum = curriculumRepository.findCurriculumByStudent(username);

        if (myCurriculum == null) {
            return new ResponseEntity<>("해당 학생의 커리큘럼을 찾을 수 없습니다.", HttpStatus.NOT_FOUND);
        }

        Pageable pageable = PageRequest.of(page, size);

        Page<FreeBoardDto> boardList = boardService.getBoardList(myCurriculum,pageable);

        if(boardList.getTotalElements() > 0){
            return new ResponseEntity<>(boardList, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // 상세보기
//    @GetMapping("/{boardId}")
//    public ResponseEntity<?> viewBoard(@PathVariable Long boardId){
//
//    }
}
