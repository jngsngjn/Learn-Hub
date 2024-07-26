package project.homelearn.dto.manager.board;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

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

    /*
    PATCH 메소드 수정 요청
    localhost:8080/managers/notification-boards/? -> board/1 ~ 100 (수정할 게시글 id값 입력)
    BODY 요청페이지에서 raw 선택 후 textarea에 밑에 값 입력(json요청)

    긴급! 공지일 때 true
    {
        "id" : 1,
        "title" : "대성진",
        "content" : "사나이",
        "emergency" : true
    }

    긴급! 공지가 아닐 때 false
    {
        "id" : 1,
        "title" : "대성진은",
        "content" : "GOAT..",
        "emergency" : false
    }

    JSON 요청할 때
    "id" : 자유롭게 변경해도 어차피 1이면 1임..
     */
}
