package project.homelearn.repository.calendar;

import org.springframework.data.jpa.repository.JpaRepository;
import project.homelearn.entity.calendar.ManagerCalendar;

public interface ManagerCalendarRepository extends JpaRepository<ManagerCalendar, Long> {
}