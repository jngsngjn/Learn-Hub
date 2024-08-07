package project.homelearn.dto.student.survey;

import lombok.Data;

import java.util.List;

// response
@Data
public class SurveyModalDto {

    private String title;

    private List<SurveyModalSubDto> contents;
}

/*
{
    "title": "네이버 클라우드 데브옵스 과정 만족도 설문 조사 2차",
    "contents": [
        {
            "id": 1,
            "content": "교육 내용에 대한 이해도",
            "type": "RATING"
        },
        {
            "id": 2,
            "content": "강사님의 교육 열의 및 준비성의 만족도",
            "type": "RATING"
        },
        {
            "id": 3,
            "content": "교육장 환경 및 시설에 대한 만족도",
            "type": "RATING"
        },
        {
            "id": 4,
            "content": "강사님의 질의응답 만족도",
            "type": "RATING"
        },
        {
            "id": 5,
            "content": "본 과정을 주위 사람에게 추천하고 싶은 정도",
            "type": "RATING"
        },
        {
            "id": 6,
            "content": "교육 전반에 대한 소감 및 개선사항에 대한 의견을 적어주세요.",
            "type": "TEXT"
        }
    ]
}
 */