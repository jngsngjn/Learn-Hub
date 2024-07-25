package project.homelearn.repository.calendar;

import org.springframework.data.jpa.repository.JpaRepository;
import project.homelearn.entity.calendar.ManagerCalendar;
import project.homelearn.repository.calendar.querydsl.ManagerCalendarRepositoryCustom;

public interface ManagerCalendarRepository extends JpaRepository<ManagerCalendar, Long>, ManagerCalendarRepositoryCustom {

}