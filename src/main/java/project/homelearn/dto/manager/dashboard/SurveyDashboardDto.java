package project.homelearn.dto.manager.dashboard;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

// 최근 설문 2개 조회 (매니저 대시보드)
@Data
public class SurveyDashboardDto {

    @NotNull
    private Long id;

    @NotNull
    private Long th;

    @NotBlank
    private String title;

    @NotNull
    private Long participants;

    @NotNull
    private Long total;

    @NotNull
    private Boolean isCompleted;
}

/*
[
    {
        "id": 2,
        "th": 2,
        "title": "네이버 클라우드 데브옵스 과정 만족도 설문 조사 1차",
        "participants": 0,
        "total": 0,
        "isCompleted": false
    },
    {
        "id": 1,
        "th": 1,
        "title": "네이버 클라우드 데브옵스 과정 만족도 설문 조사 1차",
        "participants": 1,
        "total": 1,
        "isCompleted": false
    }
]
 */