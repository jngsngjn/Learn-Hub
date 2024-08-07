package project.homelearn.repository.survey.querydsl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import project.homelearn.dto.manager.dashboard.SurveyDashboardDto;
import project.homelearn.dto.manager.manage.curriculum.CurriculumSurveyDto;
import project.homelearn.dto.manager.survey.CurriculumAndSurveyDto;
import project.homelearn.dto.manager.survey.SurveyChoiceStatisticsDto;
import project.homelearn.dto.student.survey.SurveyModalDto;

import java.util.List;
import java.util.Map;

public interface SurveyRepositoryCustom {

    CurriculumSurveyDto findProgressSurvey(Long curriculumId);

    List<CurriculumSurveyDto> findEndSurvey(Long curriculumId);

    List<SurveyDashboardDto> findSurveyTop2Dto();

    Long findSurveyCompleteCount(Long surveyId);

    Map<Integer, Integer> findSurveyBasicTrend(Long curriculumId);

    CurriculumAndSurveyDto findCurriculumAndSurvey(Long curriculumId, Long surveyId);

    List<SurveyChoiceStatisticsDto> findSurveyChoiceStatistics(Long surveyId);

    Page<String> findSurveyTextResponse(Long surveyId, Pageable pageable);

    SurveyModalDto findSurveyModal(Long surveyId);
}