package project.homelearn.dto.teacher.board;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TeacherBoardCreateDto {

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    private Boolean emergency;
}

/*
POST 메소드 생성 요청
localhost:8080/teachers/boards
{
    "curriculum" : 1,
    "title" : "공지사항1",
    "content" : "공지사항입니다만?",
    "emergency" : false
}
*/
