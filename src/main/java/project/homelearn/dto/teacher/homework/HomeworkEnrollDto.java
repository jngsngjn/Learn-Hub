package project.homelearn.dto.teacher.homework;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import project.homelearn.entity.homework.AcceptFile;

import java.time.LocalDateTime;

// request
@Data
public class HomeworkEnrollDto {

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @NotNull
    private LocalDateTime deadLine;

    @NotNull
    private Boolean requiredFile;

    private AcceptFile acceptFile;

    private MultipartFile file;
}