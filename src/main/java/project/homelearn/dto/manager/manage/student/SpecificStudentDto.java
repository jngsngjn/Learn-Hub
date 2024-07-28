package project.homelearn.dto.manager.manage.student;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import project.homelearn.entity.user.Gender;

@Data
public class SpecificStudentDto {

    @NotNull
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String email;

    @NotBlank
    private String phone;

    @NotNull
    private Gender gender;

    public SpecificStudentDto(Long id, String name, String email, String phone, Gender gender) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
    }
}