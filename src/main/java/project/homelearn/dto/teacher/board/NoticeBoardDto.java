package project.homelearn.dto.teacher.board;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
public class NoticeBoardDto {

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @NotNull
    private Boolean isEmergency;

    private MultipartFile file;
}

/*

강사 공지사항 등록
POST 요청 / localhost:8080/teachers/notification-boards
BODY form-data
key                 value
title               제목
content             내용
isEmergency         true or false
file                (타입 File로 변경 후)업로드할 파일

=========================================================
강사 공지사항 조회
GET 요청 / localhost:8080/teachers/notification-boards?page=0 -> ?page=? (원하는 page값 입력)

=========================================================
강사 공지사항 삭제
DELETE 요청 / localhost:8080/teachers/notification-boards
BODY raw에서 JSON요청
[1, 2, 3] -> [id=1, id=2, id=3]

 */