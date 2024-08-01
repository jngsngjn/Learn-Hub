package project.homelearn.dto.common.account;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EmailCodeDto {

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String code;
}

/*
{
    "email": "이메일@gmail.com",
    "code": "Q4O0C3"
}
 */