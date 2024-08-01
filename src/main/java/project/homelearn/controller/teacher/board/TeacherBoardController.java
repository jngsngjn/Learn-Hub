package project.homelearn.controller.teacher.board;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.homelearn.dto.teacher.board.TeacherBoardCreateDto;
import project.homelearn.service.teacher.TeacherBoardService;

import java.security.Principal;

/**
 * Author : 김수정
 */


@RestController
@RequiredArgsConstructor
@RequestMapping("/teachers/boards")
public class TeacherBoardController {

    private final TeacherBoardService teacherBoardService;

    @PostMapping
    public ResponseEntity<?> writeBoard(Principal principal,
                                        @Valid @ModelAttribute TeacherBoardCreateDto teacherBoardCreateDto) {
        String username = principal.getName();
        boolean result = teacherBoardService.createTeacherBoard(username, teacherBoardCreateDto);

        if (result) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // 공지 수정
    @PatchMapping("/{boardId}")
    public ResponseEntity<?> modifyBoard (@PathVariable("boardId") Long boardId, Principal principal,
                                          @Valid @ModelAttribute TeacherBoardCreateDto teacherBoardCreateDto) {
        String username = principal.getName();
        boolean result = teacherBoardService.modifyTeacherBoard(boardId, username, teacherBoardCreateDto);

        if (result) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


}
