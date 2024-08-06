package project.homelearn.dto.teacher.subject;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.querydsl.core.annotations.QueryProjection;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

// response
@Data
public class SubjectBoardTop5Dto {

    @NotNull
    private Long boardId;

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    private String fileName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime writeDate;

    @QueryProjection
    public SubjectBoardTop5Dto(Long boardId, String title, String content, String fileName, LocalDateTime writeDate) {
        this.boardId = boardId;
        this.title = title;
        this.content = content;
        this.fileName = fileName;
        this.writeDate = writeDate;
    }
}