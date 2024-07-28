package project.homelearn.dto.manager.manage.teacher;

import com.querydsl.core.annotations.QueryProjection;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import project.homelearn.entity.curriculum.CurriculumType;

@Data
public class SpecificTeacherDto {

    @NotNull
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String email;

    @NotBlank
    private String phone;

    private Long curriculumId;

    private CurriculumType type;

    private Long th;

    @QueryProjection
    public SpecificTeacherDto(Long id, String name, String email, String phone, Long curriculumId, CurriculumType type, Long th) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.curriculumId = curriculumId;
        this.type = type;
        this.th = th;
    }
}
/*
{
    "name": "수정과",
    "email": "update@gmail.com",
    "phone": "010-9999-9999",
    "curriculumId": "1",
    "type": "NCP",
    "th": 1
}

{
    "name": "teacher1",
    "email": "teacherEmail",
    "phone": "teacher1Phone",
    "curriculumId": null,
    "type": null,
    "th": null
}
 */