package project.homelearn.dto.manager.survey;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

// response
@Data
@Builder
public class CurriculumAndSurveyDto {

    @NotBlank
    private String curriculumName;

    @NotNull
    private Long th;

    private int round;

    @NotNull
    private Long completed;

    @NotNull
    private Long total;
}