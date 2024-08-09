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

 */