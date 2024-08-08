package project.homelearn.dto.student.board;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import project.homelearn.dto.common.board.QuestionBoardCommentDto;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class FreeBoardCommentDto {

    @NotNull
    private Long commentId;

    @NotBlank
    private String author;

    @NotBlank
    private String profileImageName;

    @NotBlank
    private String content;

    @NotBlank
    private LocalDateTime createTime;

    private List<FreeBoardCommentDto> replies;
}
