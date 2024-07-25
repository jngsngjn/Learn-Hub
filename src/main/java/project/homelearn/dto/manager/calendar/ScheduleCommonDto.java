package project.homelearn.dto.manager.calendar;

import lombok.Data;

import java.time.LocalDate;

// 매니저 공통 일정 등록 DTO
@Data
public class ScheduleCommonDto {

    private String title;

    private LocalDate startDate;

    private LocalDate endDate;
}

/*
{
    "title": "매니저 휴가",
    "startDate": "2024-07-25",
    "endDate": "2024-08-01"
}

{
    "title": "원장님 생일",
    "startDate": "2024-07-30"
}
 */