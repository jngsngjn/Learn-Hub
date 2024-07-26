package project.homelearn.dto.manager.dashboard;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

import java.time.LocalDate;

// 캘린더 조회 DTO
@Data
public class ScheduleDto {

    private Long id; // PK

    private String title;

    private LocalDate startDate;

    private LocalDate endDate;

    private String color;

    @QueryProjection
    public ScheduleDto(Long id, String title, LocalDate startDate, LocalDate endDate, String color) {
        this.id = id;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.color = color;
    }
}