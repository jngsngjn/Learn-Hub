package project.homelearn.repository.calendar.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import project.homelearn.dto.manager.dashboard.ManagerScheduleDto;
import project.homelearn.dto.manager.dashboard.QManagerScheduleDto;
import project.homelearn.dto.student.dashboard.QViewScheduleDto;
import project.homelearn.dto.student.dashboard.ViewScheduleDto;
import project.homelearn.dto.teacher.dashboard.QScheduleDto;
import project.homelearn.dto.teacher.dashboard.ScheduleDto;
import project.homelearn.entity.curriculum.Curriculum;

import java.util.List;

import static project.homelearn.entity.calendar.QManagerCalendar.managerCalendar;
import static project.homelearn.entity.curriculum.QCurriculum.curriculum;

@RequiredArgsConstructor
public class ManagerCalendarRepositoryImpl implements ManagerCalendarRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<ManagerScheduleDto> findAllSchedules() {
        return queryFactory
                .select(new QManagerScheduleDto(
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
    public List<ManagerScheduleDto> findCurriculumSchedules(Long curriculumId) {
        return queryFactory
                .select(new QManagerScheduleDto(
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
    public List<ScheduleDto> findManagerSchedule(Curriculum curriculum) {
        return queryFactory
                .select(new QScheduleDto(
                        managerCalendar.id,
                        managerCalendar.title,
                        managerCalendar.startDate,
                        managerCalendar.endDate))
                .from(managerCalendar)
                .where(managerCalendar.curriculum.eq(curriculum))
                .fetch();
    }

    @Override
    public List<ViewScheduleDto> findManagerScheduleOfStudent(Curriculum curriculum){
        return queryFactory
                .select(new QViewScheduleDto(
                        managerCalendar.id,
                        managerCalendar.title,
                        managerCalendar.startDate,
                        managerCalendar.endDate))
                .from(managerCalendar)
                .where(managerCalendar.curriculum.eq(curriculum))
                .fetch();
    }
}