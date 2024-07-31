package project.homelearn.dto.common;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FileDto {

    private String originalFileName; // 사용자가 올린 원본 이름
    private String uploadFileName; // UUID
    private String filePath; // 경로
}