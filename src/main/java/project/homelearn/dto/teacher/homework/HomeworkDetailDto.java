package project.homelearn.dto.teacher.homework;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import project.homelearn.entity.homework.AcceptFile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

// response
@Data
@Builder
public class HomeworkDetailDto {

    private String title;
    private String description;
    private String fileName;
    private String filePath;
    private LocalDate enrollDate;
    private Boolean requiredFile;
    private AcceptFile acceptFile;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime deadLine;

    private Long unsubmittedCount;
    private List<String> unsubmittedList;
}

/*
{
    "title": "title",
    "description": "description",
    "fileName": null,
    "filePath": null,
    "enrollDate": "2024-08-03",
    "requiredFile": true,
    "acceptFile": "JAVA",
    "deadLine": "2024-08-01 10:15",
    "unsubmittedCount": 1,
    "unsubmittedList": [
        "정성진"
    ]
}
 */