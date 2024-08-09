package project.homelearn.repository.board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project.homelearn.entity.board.comment.QuestionBoardComment;
import project.homelearn.entity.user.User;

import java.util.List;

public interface QuestionBoardCommentRepository extends JpaRepository<QuestionBoardComment, Long> {

    @Query("select q from QuestionBoardComment q where q.questionBoard.id =:questionBoardId and q.parentComment.id is null")
    List<QuestionBoardComment> findByQuestionBoardIdAndParentCommentIsNull(@Param("questionBoardId") Long questionBoardId);

    @Query("select count(distinct q.questionBoard.id) from QuestionBoardComment q where q.user = :user")
    long countDistinctQuestionCommentByUser(@Param("user") User user);
}