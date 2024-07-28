package project.homelearn.dto.manager.manage.curriculum;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CurriculumSurveyDto {

    @NotBlank
    private String title;

    @NotNull
    private Long th;

    @NotNull
    private Long completed;

    @NotNull
    private Long total;

    public CurriculumSurveyDto(String title, Long th, Long completed, Long total) {
        this.title = title;
        this.th = th;
        this.completed = completed;
        this.total = total;
    }
}