package project.homelearn.dto.teacher.subject;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class SubjectModifyDto {

    @NotBlank
    private String name;

    private String description;

    private MultipartFile image;

    private boolean useDefaultImage;
}