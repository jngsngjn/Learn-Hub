package project.homelearn.dto.manager.manage.student;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import project.homelearn.entity.student.AttendanceType;

import java.time.LocalDate;
import java.util.Map;

@Data
@AllArgsConstructor
public class StudentAttendanceDto {

    @NotNull
    private double attendanceRatio;

    @NotNull
    private Map<LocalDate, AttendanceType> dateAttendanceType;
}