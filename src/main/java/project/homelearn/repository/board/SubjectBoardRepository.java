package project.homelearn.repository.board;

import org.springframework.data.jpa.repository.JpaRepository;
import project.homelearn.entity.curriculum.SubjectBoard;
import project.homelearn.repository.board.querydsl.SubjectBoardRepositoryCustom;

public interface SubjectBoardRepository extends JpaRepository<SubjectBoard, Long>, SubjectBoardRepositoryCustom {
}
