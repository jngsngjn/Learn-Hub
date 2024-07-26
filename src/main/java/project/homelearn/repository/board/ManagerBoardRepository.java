package project.homelearn.repository.board;

import org.springframework.data.jpa.repository.JpaRepository;
import project.homelearn.entity.manager.ManagerBoard;

public interface ManagerBoardRepository extends JpaRepository<ManagerBoard, Long> {
}
