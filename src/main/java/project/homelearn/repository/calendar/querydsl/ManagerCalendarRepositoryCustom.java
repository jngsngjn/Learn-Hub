package project.homelearn.repository.calendar.querydsl;

import project.homelearn.dto.manager.dashboard.ManagerScheduleDto;
import project.homelearn.dto.student.dashboard.ViewScheduleDto;
import project.homelearn.dto.teacher.dashboard.ScheduleDto;
import project.homelearn.entity.curriculum.Curriculum;

import java.util.List;

public interface ManagerCalendarRepositoryCustom {

    List<ManagerScheduleDto> findAllSchedules();

    List<ManagerScheduleDto> findCurriculumSchedules(Long curriculumId);

    List<ScheduleDto> findManagerSchedule(Curriculum curriculum);

    List<ViewScheduleDto> findManagerScheduleOfStudent(Curriculum curriculum);
}