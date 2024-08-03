package project.homelearn.repository.user.querydsl;

import project.homelearn.entity.curriculum.Curriculum;

import java.time.LocalDate;

public interface StudentRepositoryCustom {

    Long findAttendanceCount(Curriculum curriculum);

    Long findAttendanceCountByDate(Curriculum curriculum, LocalDate date);
}