package project.homelearn.repository.survey;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import project.homelearn.entity.survey.Survey;
import project.homelearn.repository.survey.querydsl.SurveyRepositoryCustom;

public interface SurveyRepository extends JpaRepository<Survey, Long>, SurveyRepositoryCustom {

    @Query("select count(s) from Survey s where s.curriculum.id = :curriculumId")
    int findSurveyCount(@Param("curriculumId") Long curriculumId);

    @Query("select count(s) > 0 from Survey s where s.curriculum.id = :curriculumId and s.isFinished = false")
    boolean existActiveSurvey(@Param("curriculumId") Long curriculumId);

    @Modifying
    @Transactional
    @Query("update Survey s set s.isFinished = true where s.id =:id")
    void updateSurveyIsFinishedTrue(@Param("id") Long id);

    @Query("select s.isFinished from Survey s where s.id =:surveyId")
    boolean isSurveyFinished(@Param("surveyId") Long surveyId);
}