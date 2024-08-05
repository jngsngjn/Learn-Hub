package project.homelearn.repository.curriculum.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import project.homelearn.dto.teacher.lecture.LectureListDto;
import project.homelearn.dto.teacher.lecture.QLectureListDto;

import java.util.List;

import static project.homelearn.entity.curriculum.QLecture.lecture;

@RequiredArgsConstructor
public class LectureRepositoryImpl implements LectureRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<LectureListDto> findSubjectLecturePage(Long subjectId, Pageable pageable) {
        List<LectureListDto> fetch = queryFactory
                .select(new QLectureListDto(lecture.id, lecture.youtubeLink))
                .from(lecture)
                .where(lecture.subject.id.eq(subjectId))
                .orderBy(lecture.createdDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = queryFactory
                .select(lecture.id)
                .from(lecture)
                .where(lecture.subject.id.eq(subjectId))
                .fetch()
                .size();

        return new PageImpl<>(fetch, pageable, total);
    }
}