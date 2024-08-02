package project.homelearn.repository.survey.querydsl;

import project.homelearn.dto.manager.dashboard.SurveyDto;
import project.homelearn.dto.manager.manage.curriculum.CurriculumSurveyDto;

import java.util.List;

public interface SurveyRepositoryCustom {

    CurriculumSurveyDto findCurriculumSurvey(Long curriculumId);

    List<SurveyDto> findRecentSurveyDto();
}