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
    private String link;

    @QueryProjection
    public LectureListDto(Long lectureId, String link) {
        this.lectureId = lectureId;
        this.link = link;
    }
}