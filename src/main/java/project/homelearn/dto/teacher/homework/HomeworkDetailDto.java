package project.homelearn.dto.teacher.homework;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

// response
@Data
public class HomeworkDetailDto {

    private String title;
    private String description;
    private String filePath;
    private LocalDate enrollDate;
    private LocalDateTime deadLine;

    private Long unsubmittedCount;
    private List<String> unsubmittedList;
}