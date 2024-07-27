package project.homelearn.dto.manager.manage.teacher;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TeacherUpdateDto {

    @NotBlank
    private String name;

    @NotBlank
    private String phone;

    @NotBlank
    private String email;

    private Long curriculumId;
}

/*
{
    "name": "수정과",
    "phone": "010-9999-9999",
    "email": "update@gmail.com",
    "curriculumId": "1"
}
 */