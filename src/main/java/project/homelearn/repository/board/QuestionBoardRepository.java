package project.homelearn.repository.board;

import org.springframework.data.jpa.repository.JpaRepository;
import project.homelearn.entity.board.QuestionBoard;

public interface QuestionBoardRepository extends JpaRepository<QuestionBoard, Long> {
}