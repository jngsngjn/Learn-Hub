package project.homelearn.repository.survey.querydsl;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import project.homelearn.dto.manager.dashboard.SurveyDto;
import project.homelearn.dto.manager.manage.curriculum.CurriculumSurveyDto;

import java.util.ArrayList;
import java.util.List;

import static project.homelearn.entity.curriculum.QCurriculum.curriculum;
import static project.homelearn.entity.student.QStudent.student;
import static project.homelearn.entity.survey.QSurvey.survey;

@RequiredArgsConstructor
public class SurveyRepositoryImpl implements SurveyRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public CurriculumSurveyDto findCurriculumSurvey(Long curriculumId) {
        Tuple tuple = queryFactory
                .select(survey.title, survey.curriculum.th)
                .from(survey)
                .where(survey.curriculum.id.eq(curriculumId), survey.isFinished.eq(false))
                .fetchOne();
        if (tuple == null) {
            return null;
        }

        String title = tuple.get(survey.title);
        Long th = tuple.get(survey.curriculum.th);

        Long completed = queryFactory
                .select(student.count())
                .from(student)
                .where(student.surveyCompleted.eq(true), student.curriculum.id.eq(curriculumId))
                .fetchOne();

        Long total = queryFactory
                .select(student.count())
                .from(student)
                .where(student.curriculum.id.eq(curriculumId))
                .fetchOne();
        return new CurriculumSurveyDto(title, th, completed, total);
    }

    @Override
    public List<SurveyDto> findRecentSurveyDto() {
        List<Tuple> tuples = queryFactory
                .select(survey.id, survey.curriculum.th, survey.title, survey.isFinished, survey.isFinished)
                .from(survey)
                .join(survey.curriculum, curriculum)
                .orderBy(survey.id.desc())
                .limit(2)
                .fetch();

        for (Tuple tuple : tuples) {
            SurveyDto surveyDto = new SurveyDto();
            surveyDto.setId(tuple.get(survey.id));
            surveyDto.setTitle(tuple.get(survey.title));
            surveyDto.setTh(tuple.get(survey.curriculum.th));
            surveyDto.setIsCompleted(tuple.get(survey.isFinished));
        }



        List<SurveyDto> result = new ArrayList<>();
        return List.of();
    }
}