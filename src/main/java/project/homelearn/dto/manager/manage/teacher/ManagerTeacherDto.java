package project.homelearn.dto.manager.manage.teacher;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ManagerTeacherDto {

    @NotNull
    private Long teacherId;

    @NotBlank
    private String name;

    private Long curriculumTh;

    private String curriculumName;

    @NotBlank
    private String phone;

    @NotBlank
    private String email;
}