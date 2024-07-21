package project.homelearn.repository.board;

import org.springframework.data.jpa.repository.JpaRepository;
import project.homelearn.entity.curriculum.SubjectBoard;

public interface SubjectBoardRepository extends JpaRepository<SubjectBoard, Long> {
}
