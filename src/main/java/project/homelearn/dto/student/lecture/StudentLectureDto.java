package project.homelearn.dto.student.lecture;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StudentLectureDto {

    @NotNull
    private Long studentLectureId;

    @NotNull
    private Long lectureId;

    @NotNull
    private String username;

    private Long lastPosition;

    private boolean isCompleted;

    private LocalDateTime completedDate;

    private LocalDateTime initialViewDate;
}


/*
{
    "lectureId": 1,
    "username": "kimsu10",
    "lastPosition": 200,
    "isCompleted": true,
    "completedDate": "2024-08-12T00:00:00"
}
 */