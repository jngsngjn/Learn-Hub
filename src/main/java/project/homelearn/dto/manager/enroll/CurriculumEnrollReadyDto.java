package project.homelearn.dto.manager.enroll;

import lombok.Data;

import java.util.List;

// response
@Data
public class CurriculumEnrollReadyDto {

    private List<String> colors;
    private List<TeacherIdAndName> teachers;
}

/*
{
    "colors": [
        "blue",
        "green"
    ],
    "teachers": [
        "teacherId": 1,
        "teacherName": "이름"
    ]
}
 */