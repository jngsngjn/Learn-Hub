package project.homelearn.dto.teacher.dashboard;

import lombok.Data;

import java.util.Map;

@Data
public class AttendanceStateDto {

    private Long attendance;

    private Integer total;

    private Map<String, Long> weekAttendance;
}