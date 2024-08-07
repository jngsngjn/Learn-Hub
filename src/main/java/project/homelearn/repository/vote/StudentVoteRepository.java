package project.homelearn.repository.vote;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import project.homelearn.entity.user.User;
import project.homelearn.entity.vote.StudentVote;

import java.util.List;

public interface StudentVoteRepository extends JpaRepository<StudentVote, Long> {

    List<StudentVote> findAllByVoteContentId(Long voteContentId);

    @Query("select count(sv) > 0 from StudentVote sv where sv.id =:voteId and sv.user =:user")
    boolean existsByUser(Long voteId, User user);
}