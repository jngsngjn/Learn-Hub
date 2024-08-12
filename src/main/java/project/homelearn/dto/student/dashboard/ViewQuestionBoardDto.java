package project.homelearn.dto.student.dashboard;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ViewQuestionBoardDto {

    private Long questionId;

    private String subjectName;

    private String title;

    private String content;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime createdDate;

    @QueryProjection
    public ViewQuestionBoardDto(Long questionId, String subjectName, String title, String content, LocalDateTime createdDate) {
        this.questionId = questionId;
        this.subjectName = subjectName;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
    }
}
