package project.homelearn.dto.student.lecture;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class StudentLectureViewDto {

    @NotNull
    private Long lectureId;

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @NotBlank
    private String link;

    private String subjectName;
}
