package project.homelearn.dto.common.account;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CodeDto {

    @NotBlank
    private String code;
}