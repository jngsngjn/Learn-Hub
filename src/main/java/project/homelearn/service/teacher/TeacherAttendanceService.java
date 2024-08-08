package project.homelearn.service.teacher;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.homelearn.dto.teacher.dashboard.AttendanceStateDto;
import project.homelearn.entity.curriculum.Curriculum;
import project.homelearn.repository.curriculum.CurriculumRepository;
import project.homelearn.repository.user.StudentRepository;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class TeacherAttendanceService {

    private final StudentRepository studentRepository;
    private final CurriculumRepository curriculumRepository;

    public boolean isCurriculumStarted(String username) {
        Curriculum curriculum = curriculumRepository.findCurriculumByUsername(username);
        LocalDate startDate = curriculum.getStartDate();

        return startDate.isBefore(LocalDate.now()) || startDate.isEqual(LocalDate.now());
    }

    public AttendanceStateDto getAttendanceState(String username) {
        Curriculum curriculum = curriculumRepository.findCurriculumByUsername(username);
        Integer total = studentRepository.findStudentCountByCurriculum(curriculum);
        Long attendanceCount = studentRepository.findAttendanceCount(curriculum);

        AttendanceStateDto attendanceState = new AttendanceStateDto();
        attendanceState.setAttendance(attendanceCount);
        attendanceState.setTotal(total);

        LocalDate now = LocalDate.now();
        DayOfWeek today = now.getDayOfWeek();
        LocalDate startOfWeek = now.minusDays(today.getValue() - 1); // 이번 주 월요일

        Map<String, Long> weekAttendance = new LinkedHashMap<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E", Locale.KOREAN);

        for (int i = 0; i < today.getValue(); i++) {
            LocalDate date = startOfWeek.plusDays(i);
            DayOfWeek dayOfWeek = date.getDayOfWeek();

            if (dayOfWeek != DayOfWeek.SATURDAY && dayOfWeek != DayOfWeek.SUNDAY) {
                Long dailyAttendance = studentRepository.findAttendanceCountByDate(curriculum, date);
                String dayName = date.format(formatter);
                weekAttendance.put(dayName, dailyAttendance);
            }
        }

        attendanceState.setWeekAttendance(weekAttendance);
        return attendanceState;
    }
}