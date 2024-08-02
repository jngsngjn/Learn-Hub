package project.homelearn.dto.teacher.dashboard;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ManagerBoardDto {

    private Long id;
    private Boolean isEmergency;
    private String title;
    private LocalDate createdDate;
}