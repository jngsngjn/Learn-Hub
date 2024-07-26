package project.homelearn.service.student;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.homelearn.entity.student.Attendance;
import project.homelearn.entity.student.Student;
import project.homelearn.repository.user.AttendanceRepository;
import project.homelearn.repository.user.StudentRepository;

import java.time.LocalDate;
import java.util.List;

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
}