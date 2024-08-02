package project.homelearn.repository.calendar.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import project.homelearn.dto.manager.dashboard.QScheduleDto;
import project.homelearn.dto.manager.dashboard.ScheduleDto;
import project.homelearn.dto.teacher.dashboard.ManagerScheduleDto;
import project.homelearn.dto.teacher.dashboard.QManagerScheduleDto;
import project.homelearn.entity.curriculum.Curriculum;

import java.util.List;

import static project.homelearn.entity.calendar.QManagerCalendar.managerCalendar;
import static project.homelearn.entity.curriculum.QCurriculum.curriculum;

@RequiredArgsConstructor
public class ManagerCalendarRepositoryImpl implements ManagerCalendarRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<ScheduleDto> findAllSchedules() {
        return queryFactory
                .select(new QScheduleDto(
                        managerCalendar.id,
                        managerCalendar.title,
                        managerCalendar.startDate,
                        managerCalendar.endDate,
                        curriculum.color))
                .from(managerCalendar)
                .leftJoin(managerCalendar.curriculum, curriculum)
                .fetch();
    }

    @Override
    public List<ScheduleDto> findCurriculumSchedules(Long curriculumId) {
        return queryFactory
                .select(new QScheduleDto(
                        managerCalendar.id,
                        managerCalendar.title,
                        managerCalendar.startDate,
                        managerCalendar.endDate,
                        curriculum.color))
                .from(managerCalendar)
                .join(managerCalendar.curriculum, curriculum)
                .fetch();
    }

    @Override
    public List<ManagerScheduleDto> findManagerSchedule(Curriculum curriculum) {
        return queryFactory
                .select(new QManagerScheduleDto(
                        managerCalendar.id,
                        managerCalendar.title,
                        managerCalendar.startDate,
                        managerCalendar.endDate))
                .from(managerCalendar)
                .where(managerCalendar.curriculum.eq(curriculum))
                .fetch();
    }
}