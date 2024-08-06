package project.homelearn.dto.teacher.homework;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

// response
@Data
@Builder
public class HomeworkSubmitListDto {

    private Long studentHomeworkId;
    private String name;
    private String description;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime submitDate;
    private String fileName;
    private String filePath;
    private String response;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime responseDate;
}

/*
[
    {
        "studentHomeworkId": 1,
        "name": "정성진",
        "description": "dsad",
        "submitDate": "2024-08-02 20:18:43",
        "fileName": null,
        "filePath": null,
        "response": null,
        "responseDate": null
    }
]
 */