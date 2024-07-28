package project.homelearn.dto.manager.manage.curriculum;

import com.querydsl.core.annotations.QueryProjection;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CurriculumTeacherDto {

    @NotBlank
    private String name;

    @NotBlank
    private String email;

    @NotBlank
    private String phone;

    @QueryProjection
    public CurriculumTeacherDto(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }
}