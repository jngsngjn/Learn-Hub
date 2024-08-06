package project.homelearn.dto.student.survey;

import lombok.AllArgsConstructor;
import lombok.Data;
import project.homelearn.entity.survey.QuestionType;

@Data
@AllArgsConstructor
public class SurveyModalSubDto {

    private Long id;
    private String content;
    private QuestionType type;
}