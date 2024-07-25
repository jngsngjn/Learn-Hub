package project.homelearn.repository.calendar.querydsl;

import project.homelearn.dto.manager.calendar.ScheduleResponse;

import java.util.List;

public interface ManagerCalendarRepositoryCustom {

    List<ScheduleResponse> findAllSchedules();

    List<ScheduleResponse> findCurriculumSchedules(Long curriculumId);
}