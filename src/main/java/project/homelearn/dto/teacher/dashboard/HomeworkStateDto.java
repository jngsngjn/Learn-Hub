package project.homelearn.dto.teacher.dashboard;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HomeworkStateDto {

    private String title;
    private Integer submitRate;
}

/*
{
    "title": "title",
    "submitRate": 100
}
null 반환 시 과제 없음 처리
 */