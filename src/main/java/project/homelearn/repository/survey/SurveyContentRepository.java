package project.homelearn.repository.survey;

import org.springframework.data.jpa.repository.JpaRepository;
import project.homelearn.entity.survey.SurveyContent;

public interface SurveyContentRepository extends JpaRepository<SurveyContent, Long> {
}