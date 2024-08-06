package project.homelearn.dto.manager.manage.curriculum;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CurriculumSurveyDto {

    @NotNull
    private Long surveyId;

    @NotBlank
    private String title;

    @NotNull
    private Long th;

    @NotNull
    private Long completed;

    @NotNull
    private Long total;

    public CurriculumSurveyDto(Long surveyId, String title, Long th, Long completed, Long total) {
        this.surveyId = surveyId;
        this.title = title;
        this.th = th;
        this.completed = completed;
        this.total = total;
    }
}