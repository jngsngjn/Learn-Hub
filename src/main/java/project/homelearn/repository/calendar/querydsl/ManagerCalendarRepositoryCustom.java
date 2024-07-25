package project.homelearn.repository.calendar.querydsl;

import project.homelearn.dto.manager.dashboard.ScheduleDto;

import java.util.List;

public interface ManagerCalendarRepositoryCustom {

    List<ScheduleDto> findAllSchedules();

    List<ScheduleDto> findCurriculumSchedules(Long curriculumId);
}