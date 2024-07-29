package project.homelearn.dto.manager.manage.student;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import project.homelearn.entity.user.Gender;

@Data
@AllArgsConstructor
public class ManagerStudentDto {

    @NotNull
    private Long studentId;

    @NotBlank
    private String name;

    @NotNull
    private Long curriculumTh;

    @NotBlank
    private String curriculumName;

    @NotBlank
    private String phone;

    @NotNull
    private Gender gender;

    @NotBlank
    private String email;

    @NotNull
    private boolean isAttend;

}