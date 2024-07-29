package project.homelearn.dto.manager.manage.curriculum;

import lombok.Data;

@Data
public class CurriculumAttendanceDto {

    private Long attendance;
    private Integer total;
    private Integer ratio;
}

/*
{
    "attendance": 1,
    "total": 1,
    "ratio": 100
}
 */