package project.homelearn.service.manager;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.homelearn.dto.manager.calendar.ScheduleDto;
import project.homelearn.entity.calendar.ManagerCalendar;
import project.homelearn.entity.curriculum.Curriculum;
import project.homelearn.repository.calendar.ManagerCalendarRepository;
import project.homelearn.repository.curriculum.CurriculumRepository;

import java.time.LocalDate;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ManagerCalendarService {

    private final CurriculumRepository curriculumRepository;
    private final ManagerCalendarRepository managerCalendarRepository;

    public boolean addSchedule(ScheduleDto scheduleDto) {
        try {
            addScheduleProcess(scheduleDto);
            return true;
        } catch (Exception e) {
            log.error("Adding error common schedule : ", e);
            return false;
        }
    }

    private void addScheduleProcess(ScheduleDto scheduleDto) {
        ManagerCalendar calendar = new ManagerCalendar();
        calendar.setTitle(scheduleDto.getTitle());
        calendar.setStartDate(scheduleDto.getStartDate());
        LocalDate endDate = scheduleDto.getEndDate();
        if (endDate != null) {
            calendar.setEndDate(endDate);
        }

        Long curriculumId = scheduleDto.getCurriculumId();
        if (curriculumId != null) {
            Curriculum curriculum = curriculumRepository.findById(curriculumId).orElseThrow();
            calendar.setCurriculum(curriculum);
        }
        managerCalendarRepository.save(calendar);
    }

}