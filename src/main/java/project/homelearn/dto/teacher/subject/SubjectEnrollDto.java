package project.homelearn.dto.teacher.subject;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class SubjectEnrollDto {

    @NotBlank
    private String name;

    private String description;

    private MultipartFile image;
}