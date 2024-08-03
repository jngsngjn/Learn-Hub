package project.homelearn.dto.teacher.dashboard;

import lombok.Data;

import java.time.LocalDate;

@Data
public class QuestionTop5Dto {

    private String subjectName;

    private String title;

    private String content;

    private LocalDate createdDate;

    public QuestionTop5Dto(String subjectName, String title, String content, LocalDate createdDate) {
        this.subjectName = subjectName;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
    }
}

/*
[
    {
        "subjectName": "java",
        "title": "asdasd",
        "content": "asdasdasdsadasdas",
        "createdDate": "2024-08-03"
    },
    {
        "subjectName": "기타",
        "title": "asdasd",
        "content": "asdasdasdsadasdas",
        "createdDate": "2024-08-03"
    }
]
 */