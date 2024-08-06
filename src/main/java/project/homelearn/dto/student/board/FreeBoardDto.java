package project.homelearn.dto.student.board;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class FreeBoardDto {

    @NotNull
    private Long boardId;

    @NotBlank
    private String title;

    @NotBlank
    private String author;

    @NotBlank
    private LocalDateTime createDate;

    @NotNull
    private int commentCount;
}
