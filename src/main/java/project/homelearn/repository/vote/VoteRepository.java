package project.homelearn.repository.vote;

import org.springframework.data.jpa.repository.JpaRepository;
import project.homelearn.entity.vote.Vote;

import java.util.List;

public interface VoteRepository extends JpaRepository<Vote, Long>, VoteRepositoryCustom {
    List<Vote> findAllByIsFinishedFalse();

    @Query("select v.isAnonymous from Vote v where v.id =:voteId")
    boolean isAnonymousVote(@Param("voteId") Long voteId);
}