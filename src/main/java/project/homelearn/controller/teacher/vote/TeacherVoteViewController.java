package project.homelearn.controller.teacher.vote;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import project.homelearn.dto.teacher.vote.VoteTabDto;
import project.homelearn.service.teacher.TeacherVoteService;

import java.security.Principal;

/**
 * Author : 정성진
 */
@RestController
@RequestMapping("/teachers/votes")
@RequiredArgsConstructor
public class TeacherVoteViewController {

    private final TeacherVoteService voteService;

    /**
     * 투표 탭
     * 1. 진행 중인 투표 - Page(size = 1) ✅
     * 2. 마감된 투표 - Page(size = 5) ✅
     */
    @GetMapping("/progress")
    public Page<VoteTabDto> viewProgressVotes(Principal principal,
                                              @RequestParam(name = "page", defaultValue = "0") int page) {
        int size = 1;
        return voteService.getProgressVotes(principal.getName(), page, size, "진행");
    }

    @GetMapping("/completed")
    public Page<VoteTabDto> viewCompletedVotes(Principal principal,
                                              @RequestParam(name = "page", defaultValue = "0") int page) {
        int size = 5;
        return voteService.getCompletedVotes(principal.getName(), page, size, "마감");
    }
}