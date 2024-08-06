package project.homelearn.dto.teacher.dashboard;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class TeacherScheduleDto {

    @NotBlank
    private String color;

    @NotNull
    private List<ScheduleDto> scheduleDtos;
}

/*
{
    "color": "blue",
    "scheduleDtos": [
        {
            "id": 1,
            "title": "title",
            "startDate": "2024-08-01",
            "endDate": "2024-10-01"
        }
    ]
}
 */