package project.homelearn.dto.teacher.inquiry;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TeacherResponseDto {

    @NotBlank
    private String response;
}
