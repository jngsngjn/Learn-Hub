package project.homelearn.dto.common;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FileDto {

    private String originalFileName;
    private String uploadFileName;
    private String filePath;
}