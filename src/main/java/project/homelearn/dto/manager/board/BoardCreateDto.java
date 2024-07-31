package project.homelearn.dto.manager.board;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class BoardCreateDto {

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @NotNull
    private Boolean emergency;

    private MultipartFile file;

    /*
    POST 메소드 생성 요청
    localhost:8080/managers/notification-boards
    BODY요청 페이지에서 row 선택 후 textarea에 밑에 값 입력(json요청)
    {
        "title" : "대성진은",
        "content" : "GOAT..",
        "emergency" : false
    }
     */
}
