package project.homelearn.repository.user.querydsl;

import project.homelearn.entity.curriculum.Curriculum;

public interface StudentRepositoryCustom {

    Long findAttendanceCount(Curriculum curriculum);
}