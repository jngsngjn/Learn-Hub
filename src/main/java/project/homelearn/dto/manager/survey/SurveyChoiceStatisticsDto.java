package project.homelearn.dto.manager.survey;

import lombok.Data;

import java.util.Map;

// response
@Data
public class SurveyChoiceStatisticsDto {

    private String content;
    private Map<Integer, Integer> scoreResponseCount;
}

/*
[
    {
        "content": "교육 내용에 대한 이해도",
        "scoreResponseCount": {
            "1": 0,
            "2": 0,
            "3": 0,
            "4": 0,
            "5": 0
        }
    },
    {
        "content": "강사님의 교육 열의 및 준비성의 만족도",
        "scoreResponseCount": {
            "1": 0,
            "2": 0,
            "3": 0,
            "4": 0,
            "5": 0
        }
    },
    {
        "content": "교육장 환경 및 시설에 대한 만족도",
        "scoreResponseCount": {
            "1": 0,
            "2": 0,
            "3": 0,
            "4": 0,
            "5": 0
        }
    },
    {
        "content": "강사님의 질의응답 만족도",
        "scoreResponseCount": {
            "1": 0,
            "2": 0,
            "3": 0,
            "4": 0,
            "5": 0
        }
    },
    {
        "content": "본 과정을 주위 사람에게 추천하고 싶은 정도",
        "scoreResponseCount": {
            "1": 0,
            "2": 0,
            "3": 0,
            "4": 0,
            "5": 0
        }
    }
]
 */