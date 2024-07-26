package project.homelearn.dto.manager.board;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BoardCreateDto {

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @NotNull
    private Boolean emergency;
    
    /*
    POST 메소드 생성 요청
    localhost:8080/managers/notification-board
    BODY요청 페이지에서 row 선택 후 textarea에 밑에 값 입력(json요청)
    {
        "title" : "대성진은",
        "content" : "GOAT..",
        "emergency" : false
    }
     */
}
