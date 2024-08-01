package project.homelearn.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class TestDto {

    private String title;
    private String content;
    private MultipartFile image;
}