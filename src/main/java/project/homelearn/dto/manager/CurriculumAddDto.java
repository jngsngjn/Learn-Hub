package project.homelearn.dto.manager;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import project.homelearn.entity.curriculum.CurriculumType;

import java.time.LocalDate;

@Data
public class CurriculumAddDto {

    @NotNull
    private CurriculumType type;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;

    @NotBlank
    private String color;
}