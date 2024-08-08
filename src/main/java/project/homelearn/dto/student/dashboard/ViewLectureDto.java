package project.homelearn.dto.student.dashboard;

import com.querydsl.core.annotations.QueryProjection;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class ViewLectureDto {

    @NotNull
    private Long lectureId;

    @NotBlank
    private String youtubeUrl;

    @QueryProjection
    public ViewLectureDto(Long lectureId, String youtubeUrl) {
        this.lectureId = lectureId;
        this.youtubeUrl = youtubeUrl;
    }
}
