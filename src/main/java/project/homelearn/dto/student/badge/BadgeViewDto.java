package project.homelearn.dto.student.badge;

import lombok.Data;

import java.time.LocalDate;

// response
@Data
public class BadgeViewDto {

    private String name;

    private String description;

    private LocalDate obtainDate;

    private Long obtainCount;
}