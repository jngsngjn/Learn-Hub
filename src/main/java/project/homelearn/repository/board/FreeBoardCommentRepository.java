package project.homelearn.repository.board;

import org.springframework.data.jpa.repository.JpaRepository;
import project.homelearn.entity.board.comment.FreeBoardComment;

public interface FreeBoardCommentRepository extends JpaRepository<FreeBoardComment, Long> {
}