package project.homelearn.dto.common;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class FindIdRequestDto {

    @NotBlank
    private String email;
}