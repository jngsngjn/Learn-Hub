package project.homelearn.dto.student.board;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class FreeBoardWriteDto {

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    private MultipartFile image;
}