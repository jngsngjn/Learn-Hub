package project.homelearn.repository.vote.querydsl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import project.homelearn.dto.teacher.vote.VoteTabDto;
import project.homelearn.entity.curriculum.Curriculum;

public interface VoteRepositoryCustom {

    Page<VoteTabDto> findVoteTab(Curriculum curriculum, Pageable pageable, String status);

    Long findCompletedCount(Long voteId);
}