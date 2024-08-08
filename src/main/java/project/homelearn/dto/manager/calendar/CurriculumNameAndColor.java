package project.homelearn.dto.manager.calendar;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

// response
@Data
public class CurriculumNameAndColor {

    private String name;
    private String color;

    @QueryProjection
    public CurriculumNameAndColor(String name, String color) {
        this.name = name;
        this.color = color;
    }
}

/*
List로 반환
[
    {
        "name": "네이버 클라우드 데브옵스 과정 1기",
        "color": "blue"
    },
    {
        "name": "네이버 클라우드 데브옵스 과정 2기",
        "color": "green"
    }
]
 */