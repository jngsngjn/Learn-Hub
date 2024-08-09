package project.homelearn.dto.manager.board;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class BoardUpdateDto {

    @NotNull
    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @NotNull
    private Boolean emergency;

    private MultipartFile file;

    private boolean useDefaultFile;
}

/*
   JSON 요청에서 form-data 요청으로 변경
   요청 경로 : localhost:8080/managers/notification-boards/? -> ?에 수정할 해당 ID값 입력
   PATCH 요청 : Key값과 Value값으로 변경하여 전달

   게시글 수정 시, 파일 추가 방법
   Key                 Value
   title               제목
   content             내용
   emergency           true(긴급 O) or false(긴급 X)
   file                Text -> File 변경 후 추가 할 파일 넣기

   게시글 수정 시, 파일 삭제 or 파일 삭제하지 않고 수정
   Key                 Value
   title               제목을 바꿀 것이냐
   content             파일을 삭제하지 않고 글만 바꿀건가 자네?
   emergency           true(긴급 O) or false(긴급 X)
   useDefaultFile      true(파일삭제 O) or false(파일삭제 X)
*/