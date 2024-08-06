package project.homelearn.dto.teacher.lecture;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TeacherLectureViewDto {

    @NotNull
    private Long lectureId;

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @NotBlank
    private String link;

    private String subjectName;

    private int completeRate;
}