package project.homelearn.dto.manager.manage.curriculum;

import lombok.Data;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static java.lang.Math.*;

@Data
public class CurriculumBasicDto {

    private String name;

    private Long th;

    private LocalDate startDate;

    private LocalDate endDate;

    public CurriculumBasicDto(String name, Long th, LocalDate startDate, LocalDate endDate) {
        this.name = name;
        this.th = th;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Double calculateProgress() {
        if (startDate != null && endDate != null) {
            LocalDate today = LocalDate.now();
            long totalDays = ChronoUnit.DAYS.between(startDate, endDate);
            long elapsedDays = ChronoUnit.DAYS.between(startDate, today);
            double progress = (double) elapsedDays / totalDays * 100;
            if (progress < 0) {
                return 0.0;
            } else if (progress > 100) {
                return 100.0;
            } else {
                return round(progress * 10) / 10.0;
            }
        }
        return 0.0;
    }
}