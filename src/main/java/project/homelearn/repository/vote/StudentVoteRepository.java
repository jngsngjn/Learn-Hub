package project.homelearn.repository.vote;

import org.springframework.data.jpa.repository.JpaRepository;
import project.homelearn.entity.vote.StudentVote;

import java.util.List;

public interface StudentVoteRepository extends JpaRepository<StudentVote, Long> {

    List<StudentVote> findAllByVoteContentId(Long voteContentId);
}