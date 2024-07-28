package project.homelearn.dto.student.board;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CommentWriteDto {

    @NotBlank
    private String content;
}