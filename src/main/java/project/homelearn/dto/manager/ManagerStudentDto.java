package project.homelearn.dto.manager;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import project.homelearn.entity.user.Gender;

@Data
@AllArgsConstructor
public class ManagerStudentDto {

    @NotNull
    private Long studentId;

    @NotNull
    private String name;

    @NotNull
    private Long curriculumTh;

    @NotNull
    private String curriculumName;

    @NotNull
    private String phone;

    @NotNull
    private Gender gender;

    @NotNull
    private String email;

    @NotNull
    private boolean isAttend;

}
