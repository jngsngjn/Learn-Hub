package project.homelearn.dto.manager.inquiry;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ManagerResponseDto {

    @NotBlank
    private String response;
}