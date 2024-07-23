package project.homelearn.dto.manager;

import lombok.Data;
import project.homelearn.entity.curriculum.CurriculumType;

import java.time.LocalDate;

@Data
public class CurriculumAddDto {

    private String name;
    private CurriculumType type;
    private String color;
    private LocalDate startDate;
    private LocalDate endDate;
}