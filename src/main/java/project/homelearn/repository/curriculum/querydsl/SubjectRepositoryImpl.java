package project.homelearn.repository.curriculum.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import project.homelearn.dto.common.QSubjectIdAndNameDto;
import project.homelearn.dto.common.SubjectIdAndNameDto;
import project.homelearn.entity.curriculum.Curriculum;

import java.util.List;

import static project.homelearn.entity.curriculum.QSubject.subject;

@RequiredArgsConstructor
public class SubjectRepositoryImpl implements SubjectRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<SubjectIdAndNameDto> findSubjectIdsAndNames(Curriculum curriculum) {
        return queryFactory
                .select(new QSubjectIdAndNameDto(subject.id, subject.name))
                .from(subject)
                .where(subject.curriculum.eq(curriculum))
                .fetch();
    }
}
