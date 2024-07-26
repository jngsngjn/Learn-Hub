package project.homelearn.dto.common;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EmailCodeDto {

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