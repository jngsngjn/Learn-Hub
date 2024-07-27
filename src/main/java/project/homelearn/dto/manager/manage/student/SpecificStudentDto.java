package project.homelearn.dto.manager.manage.student;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import project.homelearn.entity.user.Gender;

@Data
public class SpecificStudentDto {

    @NotBlank
    private String name;

    @NotBlank
    private String email;

    @NotBlank
    private String phone;

    @NotNull
    private Gender gender;

    public SpecificStudentDto(String name, String email, String phone, Gender gender) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
    }
}