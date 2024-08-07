package project.homelearn.dto.student.homework;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class HomeworkUpdateDto {

    @NotNull
    private Long id;

    @NotBlank
    private String description;

    private MultipartFile file;

    private boolean useDefaultFile;
}