package project.homelearn.repository.calendar.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import project.homelearn.dto.teacher.dashboard.QScheduleDto;
import project.homelearn.dto.teacher.dashboard.ScheduleDto;
import project.homelearn.dto.teacher.dashboard.TeacherScheduleDto;
import project.homelearn.entity.teacher.Teacher;

import java.util.List;

import static project.homelearn.entity.calendar.QTeacherCalendar.teacherCalendar;

@RequiredArgsConstructor
public class TeacherCalendarRepositoryImpl implements TeacherCalendarRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public TeacherScheduleDto findTeacherSchedule(String color, Teacher teacher) {
        List<ScheduleDto> scheduleDtos = queryFactory
                .select(new QScheduleDto(
                        teacherCalendar.id,
                        teacherCalendar.title,
                        teacherCalendar.startDate,
                        teacherCalendar.endDate))
                .from(teacherCalendar)
                .where(teacherCalendar.user.eq(teacher))
                .fetch();

        TeacherScheduleDto result = new TeacherScheduleDto();
        result.setColor(color);
        result.setScheduleDtos(scheduleDtos);
        return result;
    }
}