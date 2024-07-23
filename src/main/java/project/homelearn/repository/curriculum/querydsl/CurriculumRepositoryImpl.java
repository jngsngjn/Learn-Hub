package project.homelearn.repository.curriculum.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import project.homelearn.entity.curriculum.CurriculumType;

import java.time.LocalDate;

import static project.homelearn.entity.curriculum.QCurriculum.curriculum;

@RequiredArgsConstructor
public class CurriculumRepositoryImpl implements CurriculumRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Long findCountByType(CurriculumType type) {
        return queryFactory
                .select(curriculum.count())
                .from(curriculum)
                .where(
                        curriculum.type.eq(type),
                        curriculum.endDate.after(LocalDate.now())
                )
                .fetchOne();
    }
}