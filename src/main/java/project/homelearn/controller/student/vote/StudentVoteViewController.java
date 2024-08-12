package project.homelearn.controller.student.vote;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.homelearn.dto.student.vote.StudentVoteViewDto;
import project.homelearn.dto.teacher.vote.VoteDetailDto;
import project.homelearn.dto.teacher.vote.VoteTabDto;
import project.homelearn.service.common.VoteCommonService;
import project.homelearn.service.student.StudentVoteService;

import java.security.Principal;

/**
 * Author : 정성진
 */
@Slf4j
@RestController
@RequestMapping("/students/votes")
@RequiredArgsConstructor
public class StudentVoteViewController {

    private final VoteCommonService voteCommonService;
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
        return voteCommonService.getProgressVotes(principal.getName(), page, size, "진행");
    }

    @GetMapping("/completed")
    public Page<VoteTabDto> viewCompletedVotes(Principal principal,
                                               @RequestParam(name = "page", defaultValue = "0") int page) {
        int size = 5;
        return voteCommonService.getCompletedVotes(principal.getName(), page, size, "마감");
    }

    /**
     * 투표 조회 - ✅
     */
    @GetMapping("/{voteId}/basic")
    public ResponseEntity<?> viewVote(@PathVariable("voteId") Long voteId,
                                      Principal principal) {
        String username = principal.getName();
        StudentVoteViewDto result = studentVoteService.getStudentVoteView(voteId, username);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * 투표 상세 조회
     * 1. 투표 참여 현황 (익명 투표 시 null 반환 or 미참여 시 null 반환) - ✅
     */
    @GetMapping("/{voteId}/detail")
    public VoteDetailDto viewVoteDetail(@PathVariable("voteId") Long voteId, Principal principal) {
        boolean participate = studentVoteService.isParticipate(voteId, principal.getName());
        if (!participate) {
            return null;
        }
        return voteCommonService.getVoteDetail(voteId);
    }
}