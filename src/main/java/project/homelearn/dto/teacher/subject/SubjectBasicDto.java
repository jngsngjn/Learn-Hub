package project.homelearn.dto.teacher.subject;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

// response
@Data
@AllArgsConstructor
public class SubjectBasicDto {

    @NotNull
    private Long subjectId;

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    private String imagePath;
}