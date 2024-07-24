package project.homelearn.dto.manager;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class StudentEnrollDto {

    @NotBlank
    private String name;

    @NotBlank
    private String email;

    @NotBlank
    private String phone;

    @NotBlank
    private String curriculumFullName;
}
/*
{
  "name": "정성진",
  "email": "wjdtjdwls98@gmail.com",
  "phone": "010-3102-6950",
  "curriculumFullName": "네이버 클라우드 데브옵스 과정 1기"
}
 */