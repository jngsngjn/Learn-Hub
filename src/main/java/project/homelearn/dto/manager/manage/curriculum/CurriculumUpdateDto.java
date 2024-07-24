package project.homelearn.dto.manager.manage.curriculum;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CurriculumUpdateDto {

    private Long teacherId;

    @NotNull
    private LocalDate startDate;

    @Future
    @NotNull
    private LocalDate endDate;

    @NotBlank
    private String color;
}

/*
{
  "teacherId": "5",
  "startDate": "2024-04-01",
  "endDate": "2024-11-30",
  "color": "#AAAAAA"
}

{
  "startDate": "2024-04-01",
  "endDate": "2024-11-30",
  "color": "#BBBBAA"
}
 */