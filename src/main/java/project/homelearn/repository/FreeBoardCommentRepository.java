package project.homelearn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.homelearn.entity.board.comment.FreeBoardComment;

public interface FreeBoardCommentRepository extends JpaRepository<FreeBoardComment, Long> {
}