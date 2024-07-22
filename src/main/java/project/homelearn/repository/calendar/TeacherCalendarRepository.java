package project.homelearn.repository.calendar;

import org.springframework.data.jpa.repository.JpaRepository;
import project.homelearn.entity.calendar.TeacherCalendar;

public interface TeacherCalendarRepository extends JpaRepository<TeacherCalendar, Long> {
}