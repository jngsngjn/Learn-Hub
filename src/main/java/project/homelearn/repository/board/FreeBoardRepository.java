package project.homelearn.repository.board;

import org.springframework.data.jpa.repository.JpaRepository;
import project.homelearn.entity.board.FreeBoard;

public interface FreeBoardRepository extends JpaRepository<FreeBoard, Long> {
}