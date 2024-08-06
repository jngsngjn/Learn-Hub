package project.homelearn.dto.common.board;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class QuestionBoardCommentDto {

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

    private List<QuestionBoardCommentDto> replies;
}
