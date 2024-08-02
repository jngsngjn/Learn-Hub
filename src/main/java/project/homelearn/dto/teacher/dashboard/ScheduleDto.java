package project.homelearn.dto.teacher.dashboard;

import com.querydsl.core.annotations.QueryProjection;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ScheduleDto {

    @NotNull
    private Long id;

    @NotBlank
    private String title;

    @NotNull
    private LocalDate startDate;

    private LocalDate endDate;

    @QueryProjection
    public ScheduleDto(Long id, String title, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}