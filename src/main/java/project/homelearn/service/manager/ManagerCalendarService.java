package project.homelearn.service.manager;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.homelearn.dto.manager.calendar.ScheduleResponse;
import project.homelearn.dto.manager.calendar.ScheduleRequest;
import project.homelearn.entity.calendar.ManagerCalendar;
import project.homelearn.entity.curriculum.Curriculum;
import project.homelearn.repository.calendar.ManagerCalendarRepository;
import project.homelearn.repository.curriculum.CurriculumRepository;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ManagerCalendarService {

    private final CurriculumRepository curriculumRepository;
    private final ManagerCalendarRepository managerCalendarRepository;

    public boolean addSchedule(ScheduleRequest scheduleRequest) {
        try {
            addScheduleProcess(scheduleRequest);
            return true;
        } catch (Exception e) {
            log.error("Adding error common schedule : ", e);
            return false;
        }
    }

    private void addScheduleProcess(ScheduleRequest scheduleRequest) {
        ManagerCalendar calendar = new ManagerCalendar();
        calendar.setTitle(scheduleRequest.getTitle());
        calendar.setStartDate(scheduleRequest.getStartDate());
        LocalDate endDate = scheduleRequest.getEndDate();
        if (endDate != null) {
            calendar.setEndDate(endDate);
        }

        Long curriculumId = scheduleRequest.getCurriculumId();
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

    public boolean updateSchedule(Long id, ScheduleRequest scheduleRequest) {
        try {
            updateScheduleProcess(id, scheduleRequest);
            return true;
        } catch (Exception e) {
            log.error("Error update manager schedule : ", e);
            return false;
        }
    }

    private void updateScheduleProcess(Long id, ScheduleRequest scheduleRequest) {
        ManagerCalendar calendar = managerCalendarRepository.findById(id).orElseThrow();

        LocalDate endDate = scheduleRequest.getEndDate();
        if (endDate == null) {
            calendar.setEndDate(null);
        } else {
            calendar.setEndDate(scheduleRequest.getEndDate());
        }

        Long curriculumId = scheduleRequest.getCurriculumId();
        if (curriculumId == null) {
            calendar.setCurriculum(null);
        } else {
            Curriculum curriculum = curriculumRepository.findById(curriculumId).orElseThrow();
            calendar.setCurriculum(curriculum);
        }

        calendar.setTitle(scheduleRequest.getTitle());
        calendar.setStartDate(scheduleRequest.getStartDate());
    }

    public List<ScheduleResponse> getCurriculumSchedules(Long id) {
        return managerCalendarRepository.findCurriculumSchedules(id);
    }

    public List<ScheduleResponse> getAllSchedules() {
        return managerCalendarRepository.findAllSchedules();
    }
}