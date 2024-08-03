package project.homelearn.dto.teacher.dashboard;

import lombok.Data;

import java.util.Map;

@Data
public class AttendanceStateDto {

    private Long attendance;

    private Integer total;

    private Map<String, Long> weekAttendance;
}

/*
{
    "attendance": 0,
    "total": 1,
    "weekAttendance": {
        "월": 0,
        "화": 0,
        "수": 0,
        "목": 1,
        "금": 1
    }
}
 */