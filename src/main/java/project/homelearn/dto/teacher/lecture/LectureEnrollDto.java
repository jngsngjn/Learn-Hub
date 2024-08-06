package project.homelearn.dto.teacher.lecture;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

// request
@Data
public class LectureEnrollDto {

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @NotBlank
    private String youtubeLink;

    private Long subjectId;
}

/*
{
    "title": "title",
    "description": "description",
    "youtubeLink": "link"
}
 */