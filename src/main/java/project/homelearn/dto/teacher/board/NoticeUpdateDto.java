package project.homelearn.dto.teacher.board;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class NoticeUpdateDto {

    @NotNull
    private Long boardId;

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @NotNull
    private Boolean isEmergency;

    private MultipartFile file;

    private boolean useDefaultFile;
}

/*

강사 공지사항 수정
PATCH 요청 / localhost:8080/teachers/notification-boards/3 -> boards/boardId (수정할 id값 입력)
BODY form-data
key                 value
title               제목
content             내용
isEmergency         true or false
file                (타입 File로 변경 후)업로드할 파일

 */