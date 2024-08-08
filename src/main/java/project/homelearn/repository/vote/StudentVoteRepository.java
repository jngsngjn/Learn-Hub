package project.homelearn.repository.vote;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import project.homelearn.entity.user.User;
import project.homelearn.entity.vote.StudentVote;
import project.homelearn.entity.vote.Vote;

import java.util.List;

public interface StudentVoteRepository extends JpaRepository<StudentVote, Long> {

    List<StudentVote> findAllByVoteContentId(Long voteContentId);

    @Query("select count(sv) > 0 from StudentVote sv where sv.voteContent.vote.id =:voteId and sv.user =:user")
    boolean isParticipate(Long voteId, User user);

    @Query("select count(sv) > 0 from StudentVote sv where sv.user.id =:userId and sv.voteContent.id =:voteContentId")
    boolean votedContent(Long userId, Long voteContentId);

    List<StudentVote> findAllByVoteAndUser(Vote vote, User student);
}