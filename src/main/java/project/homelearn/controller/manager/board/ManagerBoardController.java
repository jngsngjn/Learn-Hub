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


import java.util.List;

/**
 * Author : 동재완
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/managers")
public class ManagerBoardController {

    private final ManagerBoardService managerBoardService;

    // 공지사항 등록
    @PostMapping("/notification-boards")
    public ResponseEntity<?> writeBoard(@Valid @ModelAttribute BoardCreateDto boardCreateDto) {
        boolean result = managerBoardService.createManagerBoard(boardCreateDto);

        if (result) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // 공지사항 조회
    @GetMapping("/notification-boards")
    public ResponseEntity<?> readBoard(@RequestParam(name = "page", defaultValue = "0") int page) {
        Page<BoardReadDto> moList = managerBoardService.getManagerBoards(page, 5);

        if (moList.hasContent()) {
            return new ResponseEntity<>(moList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // 공지사항 수정
    @PatchMapping("/notification-boards/{id}")
    public ResponseEntity<?> updateBoard(@PathVariable("id") Long id,
                                         @Valid @ModelAttribute BoardUpdateDto boardUpdateDto) {
        boolean updateManager = managerBoardService.updateManagerBoard(id, boardUpdateDto);
        if (updateManager) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // 공지사항 삭제
    @DeleteMapping("/notification-boards")
    public ResponseEntity<?> deleteBoards(@RequestBody List<Long> ids) {

        boolean result = managerBoardService.deleteManagerBoards(ids);

        if (result) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}