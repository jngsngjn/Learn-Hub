package project.homelearn.dto.manager.manage.curriculum;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PasswordDto {

    @NotBlank
    private String password;
}

/*
{
  "password": "1234"
}
 */