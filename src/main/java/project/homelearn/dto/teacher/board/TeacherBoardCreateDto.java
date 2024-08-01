package project.homelearn.dto.teacher.board;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class TeacherBoardCreateDto {

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    private Boolean emergency;

//    private String uploadFileName;

    private MultipartFile uploadFile;
}

/*
POST 메소드 생성 요청
localhost:8080/teachers/boards
{
    "title" : "공지사항1",
    "content" : "공지사항입니다만?",
    "emergency" : false,
    "uploadFileName" : "text.txt"
}
*/
