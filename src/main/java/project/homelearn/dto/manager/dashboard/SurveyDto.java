package project.homelearn.dto.manager.dashboard;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

// 최근 설문 2개 조회 (매니저 대시보드)
@Data
public class SurveyDto {

    @NotNull
    private Long id;

    @NotNull
    private Long th;

    @NotBlank
    private String title;

    @NotNull
    private Integer participants;

    @NotNull
    private Integer total;

    @NotNull
    private Boolean isCompleted;
}

/*

 */