package project.homelearn.dto.manager.dashboard;

import com.querydsl.core.annotations.QueryProjection;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

// 캘린더 조회 DTO
@Data
public class ManagerScheduleDto {

    @NotNull
    private Long id; // PK

    @NotBlank
    private String title;

    @NotNull
    private LocalDate startDate;

    private LocalDate endDate;

    private String color;

    @QueryProjection
    public ManagerScheduleDto(Long id, String title, LocalDate startDate, LocalDate endDate, String color) {
        this.id = id;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.color = color;
    }
}