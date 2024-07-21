package project.homelearn.repository.board;

import org.springframework.data.jpa.repository.JpaRepository;
import project.homelearn.entity.board.comment.QuestionBoardComment;

public interface QuestionBoardCommentRepository extends JpaRepository<QuestionBoardComment, Long> {
}