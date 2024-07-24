package project.homelearn.dto.common.register;

import lombok.Data;
import project.homelearn.entity.user.Gender;

@Data
public class RegisterInfoDto {

    private String name;

    private Gender gender;

    private String phone;

    private String email;
}

/*
{
    "name": "홍길동",
    "gender": "MALE",
    "phone": "010-1234-5678",
    "email": "이메일@gmail.com"
}
 */