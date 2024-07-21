package project.homelearn.repository.survey;

import org.springframework.data.jpa.repository.JpaRepository;
import project.homelearn.entity.survey.Survey;

public interface SurveyRepository extends JpaRepository<Survey, Long> {
}