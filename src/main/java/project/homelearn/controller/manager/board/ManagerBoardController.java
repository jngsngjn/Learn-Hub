package project.homelearn.controller.manager.board;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.homelearn.dto.manager.board.BoardCreateDto;
import project.homelearn.dto.manager.board.BoardReadDto;
import project.homelearn.dto.manager.board.BoardUpdateDto;
import project.homelearn.service.manager.ManagerBoardService;

/**
 * Author : 동재완
 */
@RestController
@RequestMapping("/managers")
@RequiredArgsConstructor
public class ManagerBoardController {

    private final ManagerBoardService managerBoardService;

    // 생성
    @PostMapping("/notification-board")
    public ResponseEntity<?> writeBoard(@Valid @RequestBody BoardCreateDto boardCreateDto) {
        boolean result = managerBoardService.createManagerBoard(boardCreateDto);

        if (result) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // 조회
    @GetMapping("/notification-board")
    public ResponseEntity<?> readBoard(@RequestParam(name = "page", defaultValue = "0") int page) {
        Page<BoardReadDto> moList = managerBoardService.getManagerBoards(page, 5);

        if (moList.hasContent()) {
            return new ResponseEntity<>(moList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // 수정
    @PatchMapping("/notification-board/{id}")
    public ResponseEntity<?> updateBoard(@PathVariable("id") Long id,
                                         @Valid @RequestBody BoardUpdateDto boardUpdateDto) {
        boolean updateManager = managerBoardService.updateManagerBoard(boardUpdateDto, id);
        if (updateManager) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // 삭제
    @DeleteMapping("/notification-board/{id}")
    public ResponseEntity<?> deleteBoard(@PathVariable("id") Long id) {

        boolean result = managerBoardService.deleteManagerBoard(id);

        if (result) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}