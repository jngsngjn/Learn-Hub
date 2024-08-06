package project.homelearn.dto.teacher.subject;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.querydsl.core.annotations.QueryProjection;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

// response
@Data
public class SubjectBoardListDto {

    @NotNull
    private Long boardId;

    @NotBlank
    private String title;

    @NotBlank
    private String writer;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime writeDate;

    @NotNull
    private int viewCount;

    @QueryProjection
    public SubjectBoardListDto(Long boardId, String title, LocalDateTime writeDate, int viewCount) {
        this.boardId = boardId;
        this.title = title;
        this.writeDate = writeDate;
        this.viewCount = viewCount;
    }
}