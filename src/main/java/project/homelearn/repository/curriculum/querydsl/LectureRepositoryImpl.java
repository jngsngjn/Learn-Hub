package project.homelearn.repository.curriculum.querydsl;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import project.homelearn.dto.student.dashboard.QViewLectureDto;
import project.homelearn.dto.student.dashboard.ViewLectureDto;
import project.homelearn.dto.student.lecture.StudentLectureViewDto;
import project.homelearn.dto.teacher.lecture.LectureListDto;
import project.homelearn.dto.teacher.lecture.QLectureListDto;
import project.homelearn.dto.teacher.lecture.TeacherLectureViewDto;
import project.homelearn.dto.teacher.subject.QSubjectBoardListDto;
import project.homelearn.dto.teacher.subject.SubjectBoardListDto;
import project.homelearn.entity.curriculum.Curriculum;
import project.homelearn.entity.curriculum.Subject;
import project.homelearn.repository.curriculum.StudentLectureRepository;
import project.homelearn.repository.user.StudentRepository;

import java.util.List;
import java.util.Optional;

import static project.homelearn.entity.curriculum.QLecture.lecture;
import static project.homelearn.entity.curriculum.QStudentLecture.studentLecture;
import static project.homelearn.entity.curriculum.QSubject.subject;
import static project.homelearn.entity.curriculum.QSubjectBoard.subjectBoard;

@RequiredArgsConstructor
public class LectureRepositoryImpl implements LectureRepositoryCustom {

    private final JPAQueryFactory queryFactory;
    private final StudentRepository studentRepository;
    private final StudentLectureRepository studentLectureRepository;

    @Override
    public Page<LectureListDto> findSubjectLecturePage(Long subjectId, Pageable pageable) {
        List<LectureListDto> fetch = queryFactory
                .select(new QLectureListDto(lecture.id, lecture.title, lecture.youtubeLink, lecture.subject.id))
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

    @Override
    public Page<LectureListDto> findLecturePage(Curriculum curriculum, Pageable pageable) {
        List<LectureListDto> fetch = queryFactory
                .select(new QLectureListDto(lecture.id, lecture.title, lecture.youtubeLink))
                .from(lecture)
                .where(lecture.curriculum.eq(curriculum))
                .orderBy(lecture.createdDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = queryFactory
                .select(lecture.id)
                .from(lecture)
                .where(lecture.curriculum.eq(curriculum))
                .fetch()
                .size();
        return new PageImpl<>(fetch, pageable, total);
    }

    @Override
    public Page<SubjectBoardListDto> findSubjectBoardPage(Long subjectId, Pageable pageable, String teacherName) {
        List<SubjectBoardListDto> fetch = queryFactory
                .select(new QSubjectBoardListDto(subjectBoard.id, subjectBoard.title, subjectBoard.createdDate, subjectBoard.viewCount))
                .from(subjectBoard)
                .where(subjectBoard.subject.id.eq(subjectId))
                .orderBy(subjectBoard.createdDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        for (SubjectBoardListDto board : fetch) {
            board.setWriter(teacherName);
        }

        int total = queryFactory
                .select(subjectBoard.id)
                .from(subjectBoard)
                .where(subjectBoard.subject.id.eq(subjectId))
                .fetch()
                .size();

        return new PageImpl<>(fetch, pageable, total);
    }

    @Override
    public TeacherLectureViewDto findTeacherLectureView(Long lectureId) {
        Tuple tuple = queryFactory
                .select(lecture.id, lecture.curriculum, lecture.title, lecture.description, lecture.youtubeLink, lecture.subject)
                .from(lecture)
                .where(lecture.id.eq(lectureId))
                .fetchOne();

        if (tuple == null) {
            return null;
        }

        TeacherLectureViewDto result = new TeacherLectureViewDto();
        result.setLectureId(tuple.get(lecture.id));
        result.setTitle(tuple.get(lecture.title));
        result.setContent(tuple.get(lecture.description));
        result.setLink(tuple.get(lecture.youtubeLink));

        Subject subject = tuple.get(lecture.subject);
        if (subject != null) {
            result.setSubjectName(subject.getName());
        }

        Curriculum curriculum = tuple.get(lecture.curriculum);
        Integer total = studentRepository.findStudentCountByCurriculum(curriculum);
        Integer completeCount = studentLectureRepository.findCompleteCount(lectureId);

        if (total != null && total > 0) {
            int completionRate = (completeCount * 100) / total;
            result.setCompleteRate(completionRate);
        }

        return result;
    }

    @Override
    public Optional<ViewLectureDto> findLatestUnwatchedOrRecentLecture(String username){

        ViewLectureDto unwatchedLecture = queryFactory
                .select(new QViewLectureDto(
                        lecture.id,
                        lecture.youtubeLink
                ))
                .from(lecture)
                .leftJoin(studentLecture)
                .on(lecture.eq(studentLecture.lecture)
                        .and(studentLecture.user.username.eq(username)))
                .where(studentLecture.isNull())
                .orderBy(lecture.createdDate.desc())
                .fetchFirst();

        if(unwatchedLecture == null){
            return Optional.ofNullable(queryFactory
                    .select(new QViewLectureDto(
                            lecture.id,
                            lecture.youtubeLink
                    ))
                    .from(lecture)
                    .orderBy(lecture.createdDate.desc())
                    .fetchFirst()
            );
        }
        return Optional.of(unwatchedLecture);
    }

    @Override
    public StudentLectureViewDto findStudentLectureView(Long lectureId) {
        Tuple tuple = queryFactory
                .select(lecture.id, lecture.curriculum, lecture.title, lecture.description, lecture.youtubeLink, lecture.subject)
                .from(lecture)
                .where(lecture.id.eq(lectureId))
                .fetchOne();

        if (tuple == null) {
            return null;
        }

        StudentLectureViewDto result = new StudentLectureViewDto();
        result.setLectureId(tuple.get(lecture.id));
        result.setTitle(tuple.get(lecture.title));
        result.setContent(tuple.get(lecture.description));
        result.setLink(tuple.get(lecture.youtubeLink));

        Subject subject = tuple.get(lecture.subject);
        if (subject != null) {
            result.setSubjectName(subject.getName());
        }

        return result;
    }
}