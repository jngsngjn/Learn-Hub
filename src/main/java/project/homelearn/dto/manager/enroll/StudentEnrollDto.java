package project.homelearn.dto.manager.enroll;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.homelearn.entity.user.Gender;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentEnrollDto {

    @NotBlank
    private String name;

    @NotNull
    private Gender gender;

    @NotBlank
    private String email;

    @NotBlank
    private String phone;

    @NotBlank
    private String curriculumFullName;

}
/*
{
  "name": "홍길동",
  "gender": "MALE",
  "email": "홍길동@gmail.com",
  "phone": "010-1234-5678",
  "curriculumFullName": "네이버 클라우드 데브옵스 과정 1기"
}
 */