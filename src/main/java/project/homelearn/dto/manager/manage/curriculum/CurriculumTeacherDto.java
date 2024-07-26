package project.homelearn.dto.manager.manage.curriculum;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class CurriculumTeacherDto {

    private String name;

    private String email;

    private String phone;

    @QueryProjection
    public CurriculumTeacherDto(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }
}