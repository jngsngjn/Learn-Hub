package project.homelearn.dto.manager.manage.curriculum;

import lombok.Data;

@Data
public class CurriculumSurveyDto {

    private String title;
    private Long th;
    private Long completed;
    private Long total;

    public CurriculumSurveyDto(String title, Long th, Long completed, Long total) {
        this.title = title;
        this.th = th;
        this.completed = completed;
        this.total = total;
    }
}