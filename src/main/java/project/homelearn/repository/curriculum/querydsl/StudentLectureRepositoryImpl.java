package project.homelearn.repository.curriculum.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import project.homelearn.dto.student.dashboard.QViewRecentStudyLectureDto;
import project.homelearn.dto.student.dashboard.ViewRecentStudyLectureDto;

import java.util.Optional;

import static project.homelearn.entity.curriculum.QLecture.lecture;
import static project.homelearn.entity.curriculum.QStudentLecture.studentLecture;
import static project.homelearn.entity.curriculum.QSubject.subject;
import static project.homelearn.entity.user.QUser.user;

@RequiredArgsConstructor
public class StudentLectureRepositoryImpl implements StudentLectureRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<ViewRecentStudyLectureDto> findRecentStudyLectureByUsername(String username){

        return Optional.ofNullable(
                queryFactory
                        .select(new QViewRecentStudyLectureDto(
                                lecture.id,
                                subject.name,
                                lecture.title,
                                studentLecture.lastPosition,
                                lecture.youtubeLink
                        ))
                        .from(studentLecture)
                        .join(studentLecture.lecture, lecture)
                        .join(lecture.subject, subject)
                        .join(studentLecture.user, user)
                        .where(user.username.eq(username))
                        .orderBy(studentLecture.initialViewDate.desc())
                        .fetchFirst()
        );

    }
}
