package project.homelearn.repository.vote;

import org.springframework.data.jpa.repository.JpaRepository;
import project.homelearn.entity.vote.StudentVote;

public interface StudentVoteRepository extends JpaRepository<StudentVote, Long> {
}