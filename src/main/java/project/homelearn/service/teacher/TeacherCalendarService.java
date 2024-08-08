package project.homelearn.service.teacher;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.homelearn.dto.teacher.calendar.TeacherScheduleEnrollDto;
import project.homelearn.dto.teacher.dashboard.TeacherScheduleDto;
import project.homelearn.entity.calendar.TeacherCalendar;
import project.homelearn.entity.curriculum.Curriculum;
import project.homelearn.entity.student.Student;
import project.homelearn.entity.teacher.Teacher;
import project.homelearn.entity.user.User;
import project.homelearn.repository.calendar.TeacherCalendarRepository;
import project.homelearn.repository.curriculum.CurriculumRepository;
import project.homelearn.repository.user.TeacherRepository;
import project.homelearn.repository.user.UserRepository;

import java.time.LocalDate;

/**
 * Author : 정성진
 */
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class TeacherCalendarService {

    private final TeacherRepository teacherRepository;
    private final TeacherCalendarRepository calendarRepository;
    private final CurriculumRepository curriculumRepository;
    private final UserRepository userRepository;

    public void addSchedule(String username, TeacherScheduleEnrollDto scheduleDto) {
        TeacherCalendar calendar = new TeacherCalendar();
        calendar.setTitle(scheduleDto.getTitle());
        LocalDate startDate = scheduleDto.getStartDate();
        LocalDate endDate = scheduleDto.getEndDate();
        calendar.setStartDate(startDate);

        if (endDate != null && startDate.isAfter(endDate)) {
            log.error("StartDate is after endDate.");
            return;
        }
        calendar.setEndDate(endDate);

        Teacher teacher = teacherRepository.findByUsername(username);
        calendar.setUser(teacher);
        calendarRepository.save(calendar);
    }

    public boolean modifySchedule(String username, Long calendarId, TeacherScheduleEnrollDto scheduleDto) {
        TeacherCalendar calendar = calendarRepository.findTeacherCalendarAndTeacher(calendarId);
        String writer = calendar.getUser().getUsername();
        if (!writer.equals(username)) {
            return false;
        }

        LocalDate startDate = scheduleDto.getStartDate();
        LocalDate endDate = scheduleDto.getEndDate();
        if (endDate != null && startDate.isAfter(endDate)) {
            return false;
        }

        calendar.setTitle(scheduleDto.getTitle());
        calendar.setStartDate(startDate);
        calendar.setEndDate(endDate);
        return true;
    }

    public boolean deleteSchedule(String username, Long calendarId) {
        TeacherCalendar calendar = calendarRepository.findTeacherCalendarAndTeacher(calendarId);
        String writer = calendar.getUser().getUsername();
        if (!writer.equals(username)) {
            return false;
        }

        calendarRepository.deleteById(calendarId);
        return true;
    }

    public TeacherScheduleDto getSchedule(String username) {
        Teacher teacher = teacherRepository.findByUsernameAndCurriculum(username);
        String color = teacher.getCurriculum().getColor();
        return calendarRepository.findTeacherSchedule(color, teacher);
    }

    public TeacherScheduleDto getScheduleToStudent(String username){
        User student = userRepository.findByUsername(username);
        Curriculum curriculum = student.getCurriculum();

        Teacher teacher = teacherRepository.findByCurriculum(curriculum);
        String color = curriculum.getColor();

        return calendarRepository.findTeacherSchedule(color, teacher);
    }
}