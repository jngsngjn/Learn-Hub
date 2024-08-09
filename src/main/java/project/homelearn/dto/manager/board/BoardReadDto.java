package project.homelearn.dto.manager.board;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BoardReadDto {

    @NotBlank
    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @NotNull
    private boolean emergency;
}

/*
  GET 메소드 조회 요청
  localhost:8080/managers/notification-boards?page=? -> page=1 ~ 20.. 값 입력 (한 페이지씩 입력)

  DELETE 메소드 삭제 요청//
  localhost:8080/managers/notification-boards/? -> ? 원하는 ID값 입력해서 삭제
*/