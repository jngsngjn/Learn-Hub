package project.homelearn.dto.manager.enroll;

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

    /*
    성별 추가해야 함
     */
}
/*
{
  "name": "홍길동",
  "email": "홍길동@gmail.com",
  "phone": "010-1234-5678",
  "curriculumFullName": "네이버 클라우드 데브옵스 과정 1기"
}
 */