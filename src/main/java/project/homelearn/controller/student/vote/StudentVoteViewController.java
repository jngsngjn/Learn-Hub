package project.homelearn.controller.student.vote;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.homelearn.dto.teacher.vote.VoteTabDto;
import project.homelearn.service.common.CommonVoteService;
import project.homelearn.service.student.StudentVoteService;

import java.security.Principal;

/**
 * Author : 정성진
 */
@RestController
@RequestMapping("/students/votes")
@RequiredArgsConstructor
public class StudentVoteViewController {

    private final CommonVoteService commonVoteService;
    private final StudentVoteService studentVoteService;

    /**
     * 투표 탭
     * 1. 진행 중인 투표 - Page(size = 1) ✅
     * 2. 마감된 투표 - Page(size = 5) ✅
     */
    @GetMapping("/progress")
    public Page<VoteTabDto> viewProgressVotes(Principal principal,
                                              @RequestParam(name = "page", defaultValue = "0") int page) {
        int size = 1;
        return commonVoteService.getProgressVotes(principal.getName(), page, size, "진행");
    }

    @GetMapping("/completed")
    public Page<VoteTabDto> viewCompletedVotes(Principal principal,
                                               @RequestParam(name = "page", defaultValue = "0") int page) {
        int size = 5;
        return commonVoteService.getCompletedVotes(principal.getName(), page, size, "마감");
    }

    /**
     * 투표 상세 조회
     * 참여 전 -
     * 참여 후 -
     */
    @GetMapping("/{voteId}")
    public ResponseEntity<?> viewVote(@PathVariable("voteId") Long voteId,
                                      Principal principal) {
        String username = principal.getName();
        boolean participate = studentVoteService.isParticipate(voteId, username);
        boolean finished = studentVoteService.isVoteFinished(voteId);

        // 참여 O && 종료
        if (participate && finished) {

        }



        if (participate) { // 투표 참여 O
            return new ResponseEntity<>(HttpStatus.OK);
        }

        // 투표 참여 X
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}