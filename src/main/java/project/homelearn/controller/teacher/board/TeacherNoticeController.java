package project.homelearn.controller.teacher.board;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.homelearn.dto.teacher.board.NoticeBoardDto;
import project.homelearn.dto.teacher.board.NoticeReadDto;
import project.homelearn.dto.teacher.board.NoticeUpdateDto;
import project.homelearn.entity.curriculum.Curriculum;
import project.homelearn.repository.curriculum.CurriculumRepository;
import project.homelearn.service.teacher.TeacherBoardService;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/teachers/notification-boards")
public class TeacherNoticeController {

    private final TeacherBoardService teacherBoardService;
    private final CurriculumRepository curriculumRepository;

    // 강사 공지사항 조회
    @GetMapping
    public ResponseEntity<?> readNotice(Principal principal,
                                        @RequestParam(name = "page", defaultValue = "0") int page) {
        int size = 5;
        String username = principal.getName();
        Curriculum curriculum = curriculumRepository.findCurriculumByUsername(username);

        if (curriculum == null) {
            return new ResponseEntity<>("Not found Teacher curriculum", HttpStatus.NOT_FOUND);
        }

        Pageable pageable = PageRequest.of(page, size);
        Page<NoticeReadDto> noticeListBoard = teacherBoardService.getNoticeList(curriculum, pageable);

        if (noticeListBoard.hasContent()) {
            return new ResponseEntity<>(noticeListBoard, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // 강사 공지사항 등록
    @PostMapping
    public ResponseEntity<?> addNotice(Principal principal,
                                       @Valid @ModelAttribute NoticeBoardDto boardDto) {
        String username = principal.getName();
        boolean result = teacherBoardService.addTeacherBoard(username, boardDto);

        if (result) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // 강사 공지사항 수정
    @PatchMapping("/{boardId}")
    public ResponseEntity<?> updateNotice(@PathVariable("boardId") Long boardId,
                                          @Valid @ModelAttribute NoticeUpdateDto updateDto) {
        boolean result = teacherBoardService.updateTeacherBoard(boardId, updateDto);
        if (result) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // 강사 공지사항 삭제
    @DeleteMapping
    public ResponseEntity<?> deleteNotice(@RequestBody List<Long> boardIds) {
        boolean result = teacherBoardService.deleteTeacherBoard(boardIds);
        if (result) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}