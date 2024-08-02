package project.homelearn.dto.student.board;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class FreeBoardViewDto {

    @NotBlank
    private String title;

    @NotBlank
    private String content;
}