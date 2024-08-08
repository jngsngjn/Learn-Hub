package project.homelearn.dto.student.dashboard;

import com.querydsl.core.annotations.QueryProjection;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ViewRecentStudyLectureDto {

    @NotNull
    private Long lectureId;

    @NotBlank
    private String subjectName;

    @NotBlank
    private String title;

    @NotNull
    private Long lastPosition;

    @NotBlank
    private String youtubeUrl;

    @QueryProjection
    public ViewRecentStudyLectureDto(Long lectureId, String subjectName, String title, Long lastPosition, String youtubeUrl) {
        this.lectureId = lectureId;
        this.subjectName = subjectName;
        this.title = title;
        this.lastPosition = lastPosition;
        this.youtubeUrl = youtubeUrl;
    }
}