package project.homelearn.dto.student.homework;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class HomeworkListDto {

    @NotNull
    private Long homeworkId;

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @NotBlank
    private LocalDateTime deadLine;

    @NotNull
    private int submitCount;
}
