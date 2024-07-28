package project.homelearn.dto.student.board;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class FreeBoardWriteDto {

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    /*
    이미지 저장 추가해야 함
     */
}