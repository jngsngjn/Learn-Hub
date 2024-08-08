package project.homelearn.service.manager;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.homelearn.dto.manager.calendar.CurriculumNameAndColor;
import project.homelearn.dto.manager.calendar.ManagerScheduleEnrollDto;
import project.homelearn.dto.manager.dashboard.ManagerScheduleDto;
import project.homelearn.dto.student.dashboard.ViewScheduleDto;
import project.homelearn.dto.teacher.dashboard.ScheduleDto;
import project.homelearn.entity.calendar.ManagerCalendar;
import project.homelearn.entity.curriculum.Curriculum;
import project.homelearn.repository.calendar.ManagerCalendarRepository;
import project.homelearn.repository.curriculum.CurriculumRepository;

import java.time.LocalDate;
import java.util.List;

/**
 * Author : 정성진
 */
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ManagerCalendarService {

    private final CurriculumRepository curriculumRepository;
    private final ManagerCalendarRepository managerCalendarRepository;

    public boolean addSchedule(ManagerScheduleEnrollDto managerScheduleEnrollDto) {
        try {
            addScheduleProcess(managerScheduleEnrollDto);
            return true;
        } catch (Exception e) {
            log.error("Adding error common schedule : ", e);
            return false;
        }
    }

    private void addScheduleProcess(ManagerScheduleEnrollDto managerScheduleEnrollDto) {
        ManagerCalendar calendar = new ManagerCalendar();
        calendar.setTitle(managerScheduleEnrollDto.getTitle());
        LocalDate startDate = managerScheduleEnrollDto.getStartDate();
        calendar.setStartDate(startDate);

        LocalDate endDate = managerScheduleEnrollDto.getEndDate();
        if (endDate != null && startDate.isAfter(endDate)) {
            log.error("StartDate is after endDate.");
            throw new RuntimeException();
        }

        Long curriculumId = managerScheduleEnrollDto.getCurriculumId();
        if (curriculumId != null) {
            Curriculum curriculum = curriculumRepository.findById(curriculumId).orElseThrow();
            calendar.setCurriculum(curriculum);
        }
        managerCalendarRepository.save(calendar);
    }

    public boolean deleteSchedule(Long id) {
        if (id == null) {
            return false;
        }
        managerCalendarRepository.deleteById(id);
        return true;
    }

    public boolean updateSchedule(Long id, ManagerScheduleEnrollDto managerScheduleEnrollDto) {
        try {
            updateScheduleProcess(id, managerScheduleEnrollDto);
            return true;
        } catch (Exception e) {
            log.error("Error update manager schedule : ", e);
            return false;
        }
    }

    private void updateScheduleProcess(Long id, ManagerScheduleEnrollDto managerScheduleEnrollDto) {
        ManagerCalendar calendar = managerCalendarRepository.findById(id).orElseThrow();

        LocalDate startDate = managerScheduleEnrollDto.getStartDate();
        LocalDate endDate = managerScheduleEnrollDto.getEndDate();
        if (endDate != null && startDate.isAfter(endDate)) {
            throw new RuntimeException();
        }

        Long curriculumId = managerScheduleEnrollDto.getCurriculumId();
        if (curriculumId == null) {
            calendar.setCurriculum(null);
        } else {
            Curriculum curriculum = curriculumRepository.findById(curriculumId).orElseThrow();
            calendar.setCurriculum(curriculum);
        }

        calendar.setTitle(managerScheduleEnrollDto.getTitle());
        calendar.setStartDate(startDate);
        calendar.setEndDate(endDate);
    }

    public List<ManagerScheduleDto> getCurriculumSchedules(Long id) {
        return managerCalendarRepository.findCurriculumSchedules(id);
    }

    public List<ManagerScheduleDto> getAllSchedules() {
        return managerCalendarRepository.findAllSchedules();
    }

    public List<ScheduleDto> getAllManagerSchedules(String username) {
        Curriculum curriculum = curriculumRepository.findCurriculumByUsername(username);
        return managerCalendarRepository.findManagerSchedule(curriculum);
    }

    public List<ViewScheduleDto> getAllManagerSchedulesOfStudent(String username) {
        Curriculum curriculum = curriculumRepository.findCurriculumByUsername(username);
        return managerCalendarRepository.findManagerScheduleOfStudent(curriculum);
    }

    public List<CurriculumNameAndColor> getCurriculumNameAndColor() {
        return curriculumRepository.findCurriculumNameAndColor();
    }
}