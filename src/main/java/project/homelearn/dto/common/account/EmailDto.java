package project.homelearn.dto.common.account;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EmailDto {

    @Email
    @NotBlank
    private String email;
}