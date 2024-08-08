package project.homelearn.dto.common;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

// response
@Data
public class SubjectIdAndNameDto {

    private Long subjectId;
    private String name;

    @QueryProjection
    public SubjectIdAndNameDto(Long subjectId, String name) {
        this.subjectId = subjectId;
        this.name = name;
    }
}

/*
[
    {
        "subjectId": 1,
        "name": "java"
    }
]
 */