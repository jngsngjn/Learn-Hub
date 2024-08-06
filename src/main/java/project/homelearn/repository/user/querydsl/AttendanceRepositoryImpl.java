package project.homelearn.repository.user.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import project.homelearn.entity.student.AttendanceType;

import java.time.LocalDate;

import static project.homelearn.entity.student.QAttendance.attendance;
import static project.homelearn.entity.user.QUser.user;

@RequiredArgsConstructor
public class AttendanceRepositoryImpl implements AttendanceRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Integer countAttendanceByCurriculumId(Long curriculumId, LocalDate date) {
        Long result = queryFactory
                .select(attendance.count())
                .from(attendance)
                .join(attendance.user, user)
                .where(
                        user.curriculum.id.eq(curriculumId),
                        attendance.date.eq(date),
                        attendance.type.in(AttendanceType.ATTENDANCE, AttendanceType.LATE)
                )
                .fetchOne();

        return result != null ? result.intValue() : 0;
    }
}