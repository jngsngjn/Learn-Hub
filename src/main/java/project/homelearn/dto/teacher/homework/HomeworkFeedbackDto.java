package project.homelearn.dto.teacher.homework;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class HomeworkFeedbackDto {

    @NotBlank
    private String response;
}

/*

 */