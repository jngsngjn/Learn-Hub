package project.homelearn.dto.teacher.calendar;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TeacherScheduleEnrollDto {

    @NotBlank
    private String title;

    @NotNull
    private LocalDate startDate;

    private LocalDate endDate;
}

/*
{
    "title": "title",
    "startDate": "2024-08-01",
    "endDate": "2024-09-01"
}
 */