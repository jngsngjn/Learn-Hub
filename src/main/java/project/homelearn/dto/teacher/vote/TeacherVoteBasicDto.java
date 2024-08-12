package project.homelearn.dto.teacher.vote;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

// response
@Data
@Builder
public class TeacherVoteBasicDto {

    private Long voteId;
    private String title;
    private String description;

    private Boolean isMultiple;
    private Boolean isAnonymous;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime endTime;

    private Long total; // 총 학생 수
    private Long participantCount; // 총 투표 참여 수

    private Map<String, Long> voteCountByContent; // 항목별 투표 수
}

/*
{
    "voteId": 16,
    "title": "투표",
    "description": "투표 테스트",
    "endTime": "2024-09-10 14:00",
    "total": 1,
    "participantCount": 1,
    "voteCountByContent": {
        "투표 항목1": 1,
        "투표 항목3": 0,
        "투표 항목2": 0
    },
    "multiple": false,
    "anonymous": false,
}
 */