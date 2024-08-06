package project.homelearn.dto.teacher.subject;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

// request
@Data
public class SubjectBoardWriteDto {

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    private MultipartFile file;
}