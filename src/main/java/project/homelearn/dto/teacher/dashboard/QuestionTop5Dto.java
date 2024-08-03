package project.homelearn.dto.teacher.dashboard;

import lombok.Data;

import java.time.LocalDate;

@Data
public class QuestionTop5Dto {

    private Long questionId;

    private String subjectName;

    private String title;

    private String content;

    private LocalDate createdDate;

    public QuestionTop5Dto(Long questionId, String subjectName, String title, String content, LocalDate createdDate) {
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