package project.homelearn.repository.board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project.homelearn.entity.board.comment.FreeBoardComment;

import java.util.List;

public interface FreeBoardCommentRepository extends JpaRepository<FreeBoardComment, Long> {

    @Query("select f from FreeBoardComment f where f.freeBoard.id =:boardId and f.parentComment.id is null")
    List<FreeBoardComment> findByFreeBoardCommentIdAndParentCommentIsNull(@Param("boardId") Long boardId);
}