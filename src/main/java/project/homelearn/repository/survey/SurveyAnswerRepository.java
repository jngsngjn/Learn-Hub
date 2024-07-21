package project.homelearn.repository.survey;

import org.springframework.data.jpa.repository.JpaRepository;
import project.homelearn.entity.survey.SurveyAnswer;

public interface SurveyAnswerRepository extends JpaRepository<SurveyAnswer, Long> {
}