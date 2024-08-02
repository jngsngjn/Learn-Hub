package project.homelearn.repository.homework.querydsl;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import project.homelearn.dto.teacher.dashboard.HomeworkStateDto;
import project.homelearn.entity.curriculum.Curriculum;

import static project.homelearn.entity.homework.QHomework.homework;
import static project.homelearn.entity.homework.QStudentHomework.studentHomework;

@RequiredArgsConstructor
public class HomeworkRepositoryImpl implements HomeworkRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public HomeworkStateDto findHomeworkStateDto(Curriculum curriculum, Integer totalCount) {
        Tuple tuple = queryFactory
                .select(homework.id, homework.title)
                .from(homework)
                .where(homework.curriculum.eq(curriculum))
                .orderBy(homework.createdDate.desc())
                .limit(1)
                .fetchOne();

        if (tuple == null) {
            return null;
        }

        Long homeworkId = tuple.get(homework.id);
        String title = tuple.get(homework.title);

        Long submitCount = queryFactory
                .select(studentHomework.count())
                .from(studentHomework)
                .where(studentHomework.homework.id.eq(homeworkId))
                .fetchOne();

        // 제출률 계산 (정수로 변환)
        int submitRate = 0;
        if (submitCount != null && totalCount != null && totalCount > 0) {
            submitRate = Math.toIntExact((submitCount * 100) / totalCount);
        }

        return HomeworkStateDto
                .builder()
                .title(title)
                .submitRate(submitRate)
                .build();
    }
}