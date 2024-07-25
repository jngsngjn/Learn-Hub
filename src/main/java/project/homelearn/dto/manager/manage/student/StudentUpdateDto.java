package project.homelearn.dto.manager.manage.student;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import project.homelearn.entity.user.Gender;

@Data
public class StudentUpdateDto {

    @NotBlank
    private String name;

    @NotBlank
    private String phone;

    @NotBlank
    private String email;

    @NotNull
    private Gender gender;
}

/*
{
    "name": "수정과",
    "phone": "010-9999-9999",
    "email": "update@gmail.com",
    "gender": "FEMALE"
}
 */