package project.homelearn.repository.vote.querydsl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import project.homelearn.dto.teacher.vote.VoteBasicDto;
import project.homelearn.dto.teacher.vote.VoteDetailDto;
import project.homelearn.dto.teacher.vote.VoteTabDto;
import project.homelearn.entity.curriculum.Curriculum;

public interface VoteRepositoryCustom {

    Page<VoteTabDto> findVoteTab(Curriculum curriculum, Pageable pageable, String status);

    Long findParticipantCount(Long voteId);

    VoteBasicDto findVoteBasic(Long voteId, Curriculum curriculum);

    VoteDetailDto findVoteDetail(Long voteId);
}