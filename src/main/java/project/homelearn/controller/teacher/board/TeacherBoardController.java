package project.homelearn.controller.teacher.board;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.homelearn.dto.teacher.board.TeacherBoardCreateDto;
import project.homelearn.service.teacher.TeacherBoardService;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/teachers/boards")
public class TeacherBoardController {

    private final TeacherBoardService teacherBoardService;

    @PostMapping
    public ResponseEntity<?> writeBoard(Principal principal,
                                        @Valid @RequestBody TeacherBoardCreateDto teacherBoardCreateDto) {
        String username = principal.getName();
        boolean result = teacherBoardService.createTeacherBoard(username, teacherBoardCreateDto);

        if (result) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
