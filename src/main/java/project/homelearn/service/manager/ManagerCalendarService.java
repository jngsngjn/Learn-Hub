package project.homelearn.service.manager;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.homelearn.dto.manager.dashboard.ScheduleDto;
import project.homelearn.dto.manager.calendar.ManagerScheduleAddDto;
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

    public boolean addSchedule(ManagerScheduleAddDto managerScheduleAddDto) {
        try {
            addScheduleProcess(managerScheduleAddDto);
            return true;
        } catch (Exception e) {
            log.error("Adding error common schedule : ", e);
            return false;
        }
    }

    private void addScheduleProcess(ManagerScheduleAddDto managerScheduleAddDto) {
        ManagerCalendar calendar = new ManagerCalendar();
        calendar.setTitle(managerScheduleAddDto.getTitle());
        calendar.setStartDate(managerScheduleAddDto.getStartDate());
        LocalDate endDate = managerScheduleAddDto.getEndDate();
        if (endDate != null) {
            calendar.setEndDate(endDate);
        }

        Long curriculumId = managerScheduleAddDto.getCurriculumId();
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

    public boolean updateSchedule(Long id, ManagerScheduleAddDto managerScheduleAddDto) {
        try {
            updateScheduleProcess(id, managerScheduleAddDto);
            return true;
        } catch (Exception e) {
            log.error("Error update manager schedule : ", e);
            return false;
        }
    }

    private void updateScheduleProcess(Long id, ManagerScheduleAddDto managerScheduleAddDto) {
        ManagerCalendar calendar = managerCalendarRepository.findById(id).orElseThrow();

        LocalDate endDate = managerScheduleAddDto.getEndDate();
        if (endDate == null) {
            calendar.setEndDate(null);
        } else {
            calendar.setEndDate(managerScheduleAddDto.getEndDate());
        }

        Long curriculumId = managerScheduleAddDto.getCurriculumId();
        if (curriculumId == null) {
            calendar.setCurriculum(null);
        } else {
            Curriculum curriculum = curriculumRepository.findById(curriculumId).orElseThrow();
            calendar.setCurriculum(curriculum);
        }

        calendar.setTitle(managerScheduleAddDto.getTitle());
        calendar.setStartDate(managerScheduleAddDto.getStartDate());
    }

    public List<ScheduleDto> getCurriculumSchedules(Long id) {
        return managerCalendarRepository.findCurriculumSchedules(id);
    }

    public List<ScheduleDto> getAllSchedules() {
        return managerCalendarRepository.findAllSchedules();
    }
}