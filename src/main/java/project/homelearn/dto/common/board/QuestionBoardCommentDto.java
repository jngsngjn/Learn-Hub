package project.homelearn.dto.common.board;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class QuestionBoardCommentDto {

    @NotNull
    private Long commentId;

    @NotBlank
    private String author;

    @NotBlank
    private MultipartFile profileImage;

    @NotBlank
    private String content;

    @NotBlank
    private LocalDateTime createTime;
}
