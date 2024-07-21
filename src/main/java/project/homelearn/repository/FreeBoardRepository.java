package project.homelearn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.homelearn.entity.board.FreeBoard;

public interface FreeBoardRepository extends JpaRepository<FreeBoard, Long> {
}