package project.homelearn.repository.vote;

import org.springframework.data.jpa.repository.JpaRepository;
import project.homelearn.entity.user.User;
import project.homelearn.entity.vote.StudentVote;
import project.homelearn.entity.vote.Vote;
import project.homelearn.entity.vote.VoteContent;

import java.util.Optional;

public interface StudentVoteRepository extends JpaRepository<StudentVote, Long> {
}