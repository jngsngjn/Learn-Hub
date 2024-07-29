package project.homelearn.dto.manager.manage.curriculum;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CurriculumProgressDto {

    @NotBlank
    private String name;

    @NotNull
    private Long th;

    @NotNull
    private Double progress;

    public CurriculumProgressDto(String name, Long th, Double progress) {
        this.name = name;
        this.th = th;
        this.progress = progress;
    }
}