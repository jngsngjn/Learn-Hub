package project.homelearn.dto.student.homework;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import project.homelearn.entity.homework.AcceptFile;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class StudentHomeworkDetailDto {

    @NotNull
    private Long homeworkId;

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @NotNull
    private Boolean requiredFile;

    private AcceptFile acceptFile;

    @NotBlank
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime deadLine;

    @NotBlank
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createdDate;

    @NotBlank
    private String uploadFileName;

    @NotBlank
    private String uploadFilePath;
}

/*
{
    "homeworkId": 1,
    "title": "title",
    "description": "description",
    "requiredFile": true,
    "acceptFile": "JAVA",
    "deadLine": "2024-08-15 10:15",
    "createdDate": "2024-08-09 23:02",
    "uploadFileName": null,
    "uploadFilePath": null
}
 */