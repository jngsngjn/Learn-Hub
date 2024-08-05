package project.homelearn.dto.common.board;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class QuestionBoardDetailDto {

    @NotNull
    private Long questionBoardId;

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @NotNull
    private int viewCount;

    @NotNull
    private int scrapCount;

    @NotBlank
    private String author;

    @NotNull
    private LocalDateTime createTime;

    @NotNull
    private int commentCount;
}
