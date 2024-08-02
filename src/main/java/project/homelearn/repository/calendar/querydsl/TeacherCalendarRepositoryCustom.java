package project.homelearn.repository.calendar.querydsl;

import project.homelearn.dto.teacher.dashboard.TeacherScheduleDto;
import project.homelearn.entity.teacher.Teacher;

public interface TeacherCalendarRepositoryCustom {

    TeacherScheduleDto findTeacherSchedule(String color, Teacher teacher);
}