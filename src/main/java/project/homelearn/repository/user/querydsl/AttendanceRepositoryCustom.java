package project.homelearn.repository.user.querydsl;

import java.time.LocalDate;

public interface AttendanceRepositoryCustom {

    Integer countAttendanceByCurriculumId(Long curriculumId, LocalDate date);
}