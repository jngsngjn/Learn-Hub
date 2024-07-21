package project.homelearn.repository.board;

import org.springframework.data.jpa.repository.JpaRepository;
import project.homelearn.entity.board.scrap.QuestionScrap;

public interface QuestionScrapRepository extends JpaRepository<QuestionScrap, Long> {
}