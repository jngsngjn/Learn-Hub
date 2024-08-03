package project.homelearn.dto.student.board;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
public class QuestionBoardWriteDto {

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    private Long subjectId;

    private MultipartFile image;
}