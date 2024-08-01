package project.homelearn.dto.manager.calendar;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

// 매니저 일정 등록 DTO
@Data
public class ManagerScheduleEnrollDto {

    @NotBlank
    private String title;

    @NotNull
    private LocalDate startDate;

    private LocalDate endDate;

    private Long curriculumId;
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

{
    "title": "NCP 강의",
    "startDate": "2024-07-30",
    "endDate": "2024-08-10",
    "curriculumId": "2"
}
 */