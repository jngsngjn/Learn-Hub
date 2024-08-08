package project.homelearn.repository.vote;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project.homelearn.entity.vote.Vote;
import project.homelearn.repository.vote.querydsl.VoteRepositoryCustom;

import java.util.List;

public interface VoteRepository extends JpaRepository<Vote, Long>, VoteRepositoryCustom {
    List<Vote> findAllByIsFinishedFalse();

    @Query("select v.isAnonymous from Vote v where v.id =:voteId")
    boolean isAnonymousVote(@Param("voteId") Long voteId);

    @Query("select v.isFinished from Vote v where v.id =:voteId")
    boolean isVoteFinished(@Param("voteId") Long voteId);
}