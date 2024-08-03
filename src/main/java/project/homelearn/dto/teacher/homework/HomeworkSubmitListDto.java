package project.homelearn.dto.teacher.homework;

import lombok.Data;

import java.time.LocalDate;

// response
@Data
public class HomeworkSubmitListDto {

    private String name;
    private String description;
    private LocalDate submitDate;
    private String filePath;
    private String response;
    private LocalDate responseDate;
}