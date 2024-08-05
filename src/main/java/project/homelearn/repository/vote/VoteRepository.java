package project.homelearn.repository.vote;

import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import project.homelearn.entity.vote.Vote;
import project.homelearn.repository.vote.querydsl.VoteRepositoryCustom;

public interface VoteRepository extends JpaRepository<Vote, Long>, VoteRepositoryCustom {

    @Query("select v.isAnonymous from Vote v where v.id =:voteId")
    boolean isAnonymousVote(@Param("voteId") Long voteId);
}