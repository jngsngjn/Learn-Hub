package project.homelearn.repository.curriculum;

import org.springframework.data.jpa.repository.JpaRepository;
import project.homelearn.entity.calendar.Calendar;

public interface CalendarRepository extends JpaRepository<Calendar, Long> {
}