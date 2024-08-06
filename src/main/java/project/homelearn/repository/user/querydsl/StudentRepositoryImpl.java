package project.homelearn.repository.user.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import project.homelearn.entity.curriculum.Curriculum;

import java.time.LocalDate;

import static project.homelearn.entity.student.AttendanceType.ATTENDANCE;
import static project.homelearn.entity.student.AttendanceType.LATE;
import static project.homelearn.entity.student.QAttendance.attendance;
import static project.homelearn.entity.student.QStudent.student;

@RequiredArgsConstructor
public class StudentRepositoryImpl implements StudentRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Long findAttendanceCount(Curriculum curriculum) {
        return queryFactory
                .select(student.count())
                .from(student)
                .where(
                        student.curriculum.eq(curriculum),
                        attendance.type.in(ATTENDANCE, LATE),
                        attendance.date.eq(LocalDate.now())
                )
                .join(student.attendances, attendance)
                .fetchOne();
    }

    @Override
    public Long findAttendanceCountByDate(Curriculum curriculum, LocalDate date) {
        return queryFactory
                .select(student.count())
                .from(student)
                .where(
                        student.curriculum.eq(curriculum),
                        attendance.type.in(ATTENDANCE, LATE),
                        attendance.date.eq(date)
                )
                .join(student.attendances, attendance)
                .fetchOne();
    }
}