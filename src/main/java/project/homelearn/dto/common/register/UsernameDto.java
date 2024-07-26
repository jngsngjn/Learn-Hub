package project.homelearn.dto.common.register;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UsernameDto {

    @NotBlank
    private String username;
}

/*
{
    "username": "sonny"
}
 */