package project.homelearn.service.student;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.homelearn.dto.manager.manage.student.StudentAttendanceDto;
import project.homelearn.entity.student.Attendance;
import project.homelearn.entity.student.AttendanceType;
import project.homelearn.entity.student.Student;
import project.homelearn.repository.user.AttendanceRepository;
import project.homelearn.repository.user.StudentRepository;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static java.lang.Math.*;
import static project.homelearn.entity.student.AttendanceType.ABSENT;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AttendanceService {

    private final StudentRepository studentRepository;
    private final AttendanceRepository attendanceRepository;

    @Scheduled(cron = "0 0 14 * * *") // 매일 오후 2시에 실행
    public void autoAbsentProcess() {
        List<Student> absentStudents = studentRepository.findAbsentStudents();
        for (Student student : absentStudents) {
            LocalDate now = LocalDate.now();
            Attendance attendance = new Attendance(student, ABSENT, now);
            attendanceRepository.save(attendance);
        }
    }

    // 출석현황
    // 해당날짜에 출석, 지각, 결석인지 Key : Value 로 return
    public StudentAttendanceDto getStudentAttendance(Long studentId) {
        Map<LocalDate, AttendanceType> dateAttendanceType = new ConcurrentHashMap<>();
        List<Attendance> attendances = attendanceRepository.findByUserId(studentId);

        for (Attendance attendance : attendances) {
            dateAttendanceType.put(attendance.getDate(), attendance.getType());
        }
        return new StudentAttendanceDto(
                studentAttendanceRatio(studentId),
                dateAttendanceType
        );
    }

    // 출결율
    // 내 출석 현황 : 지금까지 한 수업 일수중에 출석 + 지각일을 퍼센티지로
    private double studentAttendanceRatio(Long studentId) {
        int attendanceCount = attendanceRepository.findByStudentId(studentId);
        int daysUntilNow = (int) ChronoUnit.DAYS.between(studentRepository.findCurriculumByStudentId(studentId).getStartDate(), LocalDate.now());

        double result = ((double) attendanceCount / (double) daysUntilNow) * 100;

        if (result < 0) {
            return 0.0;
        } else if (result > 100) {
            return 100.0;
        }
        else {
            return round(result * 10) / 10.0;
        }
    }
}