package project.homelearn.service.manager;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.homelearn.dto.manager.calendar.ScheduleCommonDto;
import project.homelearn.entity.calendar.ManagerCalendar;
import project.homelearn.repository.calendar.ManagerCalendarRepository;

import java.time.LocalDate;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ManagerCalendarService {

    private final ManagerCalendarRepository managerCalendarRepository;

    public boolean addCommonSchedule(ScheduleCommonDto scheduleDto) {
        try {
            ManagerCalendar calendar = new ManagerCalendar();
            calendar.setTitle(scheduleDto.getTitle());
            calendar.setStartDate(scheduleDto.getStartDate());
            LocalDate endDate = scheduleDto.getEndDate();
            if (endDate != null) {
                calendar.setEndDate(endDate);
            }
            managerCalendarRepository.save(calendar);
            return true;
        } catch (Exception e) {
            log.error("Adding error common schedule : ", e);
            return false;
        }
    }

}