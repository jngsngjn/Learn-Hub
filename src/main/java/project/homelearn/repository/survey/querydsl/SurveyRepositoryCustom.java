package project.homelearn.repository.survey.querydsl;

import project.homelearn.dto.manager.manage.curriculum.CurriculumSurveyDto;

public interface SurveyRepositoryCustom {

    CurriculumSurveyDto findCurriculumSurvey(Long curriculumId);
}