package project.homelearn.repository.survey.querydsl;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import project.homelearn.dto.manager.dashboard.SurveyDto;
import project.homelearn.dto.manager.manage.curriculum.CurriculumSurveyDto;

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

        if (tuples == null) {
            return null;
        }

        Tuple tuple1 = tuples.get(0);
        Tuple tuple2 = tuples.get(1);
        Long surveyId1 = tuple1.get(survey.id);
        Long surveyId2 = tuple2.get(survey.id);

        SurveyDto survey1 = new SurveyDto();
        survey1.setId(surveyId1);
        survey1.setTitle(tuple1.get(survey.title));
        survey1.setTh(tuple1.get(survey.curriculum.th));
        survey1.setIsCompleted(tuple1.get(survey.isFinished));

        SurveyDto survey2 = new SurveyDto();
        survey2.setId(surveyId2);
        survey2.setTitle(tuple2.get(survey.title));
        survey2.setTh(tuple2.get(survey.curriculum.th));
        survey2.setIsCompleted(tuple2.get(survey.isFinished));


        return List.of();
    }
}