package project.homelearn.dto.teacher;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class QuestionBoardListDto {

    @NotNull
    private Long questionBoardId;

    @NotBlank
    private String title;

    private String subjectName;

    @NotNull
    private LocalDateTime createDate;

    @NotBlank
    private String content;
}
