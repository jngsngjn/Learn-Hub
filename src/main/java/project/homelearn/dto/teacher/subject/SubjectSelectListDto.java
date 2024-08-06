package project.homelearn.dto.teacher.subject;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SubjectSelectListDto {

    @NotNull
    private Long subjectId;

    @NotBlank
    private String name;
}