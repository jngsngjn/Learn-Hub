package project.homelearn.repository.user.querydsl;

import com.querydsl.jpa.impl.JPAQuery;
import project.homelearn.entity.student.Attendance;

import java.time.LocalDate;

public interface AttendanceRepositoryCustom {
    Integer countAttendanceByCurriculumId(Long curriculumId, LocalDate date);
}
