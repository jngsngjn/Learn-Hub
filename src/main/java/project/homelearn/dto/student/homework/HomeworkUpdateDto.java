package project.homelearn.dto.student.homework;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class HomeworkUpdateDto {

    @NotNull
    private Long homeworkId;

    @NotBlank
    private String description;

    private MultipartFile file;

    private boolean useDefaultFile;
}

/*
  JSON 요청 BODY 체크 후, form-data 선택. Key값과 Value값으로 요청
  요청 경로 : localhost:8080/students/homeworks/?  (변경할 id값 입력)
  POST 요청
  key               value
  homeworkId        1
  description       수정할 내용
  file              수정할 업로드할 파일
*/