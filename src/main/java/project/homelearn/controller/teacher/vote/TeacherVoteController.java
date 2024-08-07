package project.homelearn.controller.teacher.vote;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.homelearn.dto.teacher.vote.VoteCreateDto;
import project.homelearn.service.teacher.TeacherVoteService;

import java.security.Principal;

/**
 * Author : 동재완
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/teachers/votes")
public class TeacherVoteController {

    private final TeacherVoteService teacherVoteService;

    // 투표 등록 (학생에게 알림 보내야 함)
    @PostMapping
    public ResponseEntity<?> createVote(Principal principal, @Valid @RequestBody VoteCreateDto voteCreateDto) {
        String username = principal.getName();
        boolean result = teacherVoteService.createVote(voteCreateDto, username);

        if (result) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // 투표 삭제
    @DeleteMapping("/{voteId}")
    public ResponseEntity<?> deleteVote(@PathVariable("voteId") Long voteId) {
        boolean result = teacherVoteService.deleteVote(voteId);
        if (result) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // 투표 마감
    @PostMapping("/{voteId}")
    public ResponseEntity<?> finishVote(@PathVariable("voteId") Long voteId) {
        boolean result = teacherVoteService.finishVotes(voteId);
        if (result) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}