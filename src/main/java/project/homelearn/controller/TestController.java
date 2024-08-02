package project.homelearn.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import project.homelearn.dto.student.board.FreeBoardViewDto;
import project.homelearn.dto.student.board.FreeBoardWriteDto;
import project.homelearn.entity.board.FreeBoard;
import project.homelearn.repository.board.FreeBoardRepository;
import project.homelearn.service.student.StudentBoardService;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TestController {

    private final StudentBoardService boardService;
    private final FreeBoardRepository boardRepository;

    @PostMapping("/test/editor")
    public ResponseEntity<?> test(@ModelAttribute FreeBoardWriteDto boardDto) {
        boardService.writeBoard("jngsngjn", boardDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/test/view")
    public FreeBoardViewDto view() {
        FreeBoard board = boardRepository.findById(10L).orElseThrow();
        FreeBoardViewDto boardDto = new FreeBoardViewDto();

        boardDto.setTitle(board.getTitle());
        boardDto.setContent(board.getContent());
        return boardDto;
    }
}