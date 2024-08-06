package project.homelearn.repository.vote;

import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import project.homelearn.entity.vote.VoteContent;

import java.util.List;

public interface VoteContentRepository extends JpaRepository<VoteContent, Long> {

    @Query("select vc from VoteContent vc where vc.vote.id =:voteId")
    List<VoteContent> findByVoteId(@Param("voteId") Long voteId);
}