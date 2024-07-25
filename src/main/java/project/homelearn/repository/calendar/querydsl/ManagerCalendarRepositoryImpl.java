package project.homelearn.repository.calendar.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import project.homelearn.dto.manager.calendar.QScheduleResponse;
import project.homelearn.dto.manager.calendar.ScheduleResponse;

import java.util.List;

import static project.homelearn.entity.calendar.QManagerCalendar.managerCalendar;
import static project.homelearn.entity.curriculum.QCurriculum.curriculum;

@RequiredArgsConstructor
public class ManagerCalendarRepositoryImpl implements ManagerCalendarRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<ScheduleResponse> findAllSchedules() {
        return queryFactory
                .select(new QScheduleResponse(
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
    public List<ScheduleResponse> findCurriculumSchedules(Long curriculumId) {
        return queryFactory
                .select(new QScheduleResponse(
                        managerCalendar.id,
                        managerCalendar.title,
                        managerCalendar.startDate,
                        managerCalendar.endDate,
                        curriculum.color))
                .from(managerCalendar)
                .join(managerCalendar.curriculum, curriculum)
                .fetch();
    }
}