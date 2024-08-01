package project.homelearn.repository.board;

import org.springframework.data.jpa.repository.JpaRepository;
import project.homelearn.entity.board.QuestionBoard;

import java.time.LocalDateTime;
import java.util.List;

public interface QuestionBoardRepository extends JpaRepository<QuestionBoard, Long> {

    List<QuestionBoard> findByCreatedDateBeforeAndCommentsIsNull(LocalDateTime dateTime);
}