package project.homelearn.dto.teacher.homework;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

// page response
@Data
public class HomeworkTabDto {

    private Long homeworkId;

    private String title;

    private String description;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime deadLine;

    private Long submitCount;
}

/*
{
    "totalElements": 3,
    "totalPages": 3,
    "first": true,
    "last": false,
    "size": 1,
    "content": [
        {
            "homeworkId": 4,
            "title": "title",
            "description": "description",
            "deadLine": "2024-08-20 10:15",
            "submitCount": 0
        }
    ],
    "number": 0,
    "sort": {
        "empty": true,
        "unsorted": true,
        "sorted": false
    },
    "pageable": {
        "pageNumber": 0,
        "pageSize": 1,
        "sort": {
            "empty": true,
            "unsorted": true,
            "sorted": false
        },
        "offset": 0,
        "unpaged": false,
        "paged": true
    },
    "numberOfElements": 1,
    "empty": false
}
 */