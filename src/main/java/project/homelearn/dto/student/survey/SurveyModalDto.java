package project.homelearn.dto.student.survey;

import lombok.Data;

import java.util.List;

// response
@Data
public class SurveyModalDto {

    private String title;

    private List<SurveyModalSubDto> contents;
}