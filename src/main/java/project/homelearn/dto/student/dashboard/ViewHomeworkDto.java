package project.homelearn.dto.student.dashboard;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.querydsl.core.annotations.QueryProjection;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ViewHomeworkDto {

    @NotNull
    private Long homeworkId;

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @NotBlank
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime deadLine;

    @NotNull
    private boolean isSubmit;

    @QueryProjection
    public ViewHomeworkDto(Long homeworkId, String title, String description, LocalDateTime deadLine, boolean isSubmit) {
        this.homeworkId = homeworkId;
        this.title = title;
        this.description = description;
        this.deadLine = deadLine;
        this.isSubmit = isSubmit;
    }
}
