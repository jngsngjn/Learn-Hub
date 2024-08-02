package project.homelearn.dto.teacher.dashboard;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class NewsDto {

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @NotBlank
    private String url;

    @NotBlank
    private String imageUrl;
}