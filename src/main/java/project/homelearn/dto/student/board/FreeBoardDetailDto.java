package project.homelearn.dto.student.board;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class FreeBoardDetailDto {

    @NotNull
    private Long boardId;

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @NotNull
    private int viewCount;

    @NotBlank
    private String author;

    @NotBlank
    private String username;

    @NotNull
    private LocalDateTime createTime;

    @NotNull
    private int commentCount;
}
