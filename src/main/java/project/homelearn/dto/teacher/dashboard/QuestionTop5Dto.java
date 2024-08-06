package project.homelearn.dto.teacher.dashboard;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class QuestionTop5Dto {

    private Long questionId;

    private String subjectName;

    private String title;

    private String content;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime createdDate;

    @QueryProjection
    public QuestionTop5Dto(Long questionId, String subjectName, String title, String content, LocalDateTime createdDate) {
        this.questionId = questionId;
        this.subjectName = subjectName;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
    }
}

/*
[
    {
        "questionId": "2",
        "subjectName": "java",
        "title": "asdasd",
        "content": "asdasdasdsadasdas",
        "createdDate": "2024-08-03"
    },
    {
        "questionId": "1",
        "subjectName": "기타",
        "title": "asdasd",
        "content": "asdasdasdsadasdas",
        "createdDate": "2024-08-03"
    }
]
 */