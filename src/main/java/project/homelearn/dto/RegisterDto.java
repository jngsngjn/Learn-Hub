package project.homelearn.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import project.homelearn.entity.user.Gender;

@Data
public class RegisterDto {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    private String name;

    private Gender gender;

    @NotBlank
    private String phone;

    @NotBlank
    private String email;
}

/*
학생
{
    "username": "jngsngjn",
    "password": "1234",
    "name": "정성진",
    "gender": "MALE",
    "phone": "010-1222-1333",
    "email": "wjdtjdwls93@gmail.com"
}

강사
{
    "username": "sonny",
    "password": "1234",
    "name": "손흥민",
    "phone": "010-1222-1343",
    "email": "jkj1544@naver.com"
}
 */