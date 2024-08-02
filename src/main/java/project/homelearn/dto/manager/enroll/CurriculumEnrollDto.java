package project.homelearn.dto.manager.enroll;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import project.homelearn.entity.curriculum.CurriculumType;

import java.time.LocalDate;

@Data
public class CurriculumEnrollDto {

    @NotNull
    private CurriculumType type;

    @NotNull
    private LocalDate startDate;

    @Future
    @NotNull
    private LocalDate endDate;

    @NotBlank
    private String color;

    private Long teacherId;
}

/*
{
  "type": "NCP",
  "startDate": "2024-02-22",
  "endDate": "2024-10-22",
  "color": "#ASFSAF"
}

강사 등록 시
{
  "type": "NCP",
  "startDate": "2024-02-22",
  "endDate": "2024-10-22",
  "color": "blue",
  "teacherId": "2"
}
 */