package project.homelearn.repository.curriculum.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import project.homelearn.dto.teacher.lecture.LectureListDto;
import project.homelearn.dto.teacher.lecture.QLectureListDto;

import java.util.List;

import static project.homelearn.entity.curriculum.QLecture.lecture;

@RequiredArgsConstructor
public class LectureRepositoryImpl implements LectureRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<LectureListDto> findLectureListTop6(Long subjectId) {
        return queryFactory
                .select(new QLectureListDto(lecture.id, lecture.youtubeLink))
                .from(lecture)
                .where(lecture.subject.id.eq(subjectId))
                .orderBy(lecture.createdDate.desc())
                .limit(6)
                .fetch();
    }
}