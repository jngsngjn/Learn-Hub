package project.homelearn.dto.teacher.board;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class NoticeReadDto {

    @NotNull
    private Long boardId;

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
    private LocalDateTime createDate;

    @NotNull
    private Boolean isEmergency;

    private String filePath;
    private String uploadFileName;
}