package project.homelearn.dto.teacher.lecture;

import com.querydsl.core.annotations.QueryProjection;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LectureListDto {

    @NotNull
    private Long lectureId;

    @NotBlank
    private String title;

    @NotBlank
    private String link;

    @QueryProjection
    public LectureListDto(Long lectureId, String title, String link) {
        this.title = title;
        this.lectureId = lectureId;
        this.link = link;
    }
}