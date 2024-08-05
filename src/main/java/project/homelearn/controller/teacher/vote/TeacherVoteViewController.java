package project.homelearn.controller.teacher.vote;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import project.homelearn.dto.teacher.vote.VoteBasicDto;
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
     * 투표 탭 (재완이형 받고 테스트해야 함)
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

    /**
     * 투표 상세 조회
     * 1. 투표 상세 내용 (PK, 제목, 내용, 투표 항목, 총원, 항목별 투표 인원수, 총 투표 참여 인원수, 복수 선택 여부, 익명 여부, 마감일시)
     * 2. 투표 참여 현황 (전체 투표 인원 이름, 항목 이름, 항목별 투표 인원수, 항목별 투표 인원 이름)
     */
    @GetMapping("/{voteId}/basic")
    public VoteBasicDto viewVoteBasic(@PathVariable("voteId") Long voteId) {
        return null;
    }


//    @GetMapping("/{voteId}/detail")

}