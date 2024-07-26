package project.homelearn.dto.manager.manage.curriculum;

import lombok.Data;

@Data
public class CurriculumProgressDto {
    private String name;
    private Long th;
    private Double progress;

    public CurriculumProgressDto(String name, Long th, Double progress) {
        this.name = name;
        this.th = th;
        this.progress = progress;
    }
}