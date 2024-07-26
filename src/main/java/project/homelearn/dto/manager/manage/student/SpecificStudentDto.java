package project.homelearn.dto.manager.manage.student;

import lombok.Data;
import project.homelearn.entity.user.Gender;

@Data
public class SpecificStudentDto {

    private String name;

    private String email;

    private String phone;

    private Gender gender;

    public SpecificStudentDto(String name, String email, String phone, Gender gender) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
    }
}