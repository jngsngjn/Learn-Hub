package project.homelearn.controller.student.vote;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.homelearn.service.student.StudentVoteService;

import java.security.Principal;
import java.util.Map;

/**
 * Author : 정성진
 */
@RestController
@RequestMapping("/students/votes")
@RequiredArgsConstructor
public class StudentVoteController {

    private final StudentVoteService voteService;

    /*
    투표 참여
    {
      "1": true,
      "2": false,
      "3": true,
      "4": true,
      "5": true
    }
     */
    @PostMapping("/{voteId}")
    public ResponseEntity<?> participateVote(@PathVariable("voteId") Long voteId,
                                             Principal principal,
                                             @RequestBody Map<Long, Boolean> voteResult) {
        String username = principal.getName();
        boolean result = voteService.participateVote(voteId, username, voteResult);
        if (result) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    // 투표 수정
    @PatchMapping("/{voteId}")
    public ResponseEntity<?> modifyVote(@PathVariable("voteId") Long voteId,
                                        Principal principal,
                                        @RequestBody Map<Long, Boolean> voteResult) {
        String username = principal.getName();
        boolean result = voteService.modifyVote(voteId, username, voteResult);
        if (result) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}