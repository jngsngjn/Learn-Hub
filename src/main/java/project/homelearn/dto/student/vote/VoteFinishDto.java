package project.homelearn.dto.student.vote;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

// response
@Data
public class VoteFinishDto {

    private Long voteId;
    private String title;
    private String description;

    private Boolean isMultiple;
    private Boolean isAnonymous;
    private Integer total;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime endTime;

    private List<VContent> vContents;

    @Data
    public static class VContent {
        private String content;
        private int count;
        private Boolean voted;
    }
}

/*
{
    "voteId": 13,
    "title": "투표",
    "description": "투표 테스트",
    "isMultiple": false,
    "isAnonymous": false,
    "total": 30,
    "endTime": "2024-08-07 20:48",
    "vcontents": [
        {
            "content": "투표 항목1",
            "count": 1,
            "voted": true
        },
        {
            "content": "투표 항목2",
            "count": 0,
            "voted": false
        },
        {
            "content": "투표 항목3",
            "count": 0,
            "voted": false
        }
    ]
}
 */