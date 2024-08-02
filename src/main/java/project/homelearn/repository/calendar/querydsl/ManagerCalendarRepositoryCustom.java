package project.homelearn.repository.calendar.querydsl;

import project.homelearn.dto.manager.dashboard.ScheduleDto;
import project.homelearn.dto.teacher.dashboard.ManagerScheduleDto;
import project.homelearn.entity.curriculum.Curriculum;

import java.util.List;

public interface ManagerCalendarRepositoryCustom {

    List<ScheduleDto> findAllSchedules();

    List<ScheduleDto> findCurriculumSchedules(Long curriculumId);

    List<ManagerScheduleDto> findManagerSchedule(Curriculum curriculum);
}