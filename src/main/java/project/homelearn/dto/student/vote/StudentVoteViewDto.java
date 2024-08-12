package project.homelearn.dto.student.vote;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

// response
@Data
public class StudentVoteViewDto {

    private Long voteId;
    private String title;
    private String description;

    private Boolean isMultiple;
    private Boolean isAnonymous;
    private Integer total;
    private Long participateCount;
    private Boolean participate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime endTime;

    private List<VContent> vContents;

    @Data
    public static class VContent {
        private Long contentId;
        private String content;
        private int count;
        private Boolean voted;
    }
}

/*
{
    "voteId": 1,
    "title": "투표",
    "description": "투표 테스트",
    "isMultiple": true,
    "isAnonymous": false,
    "total": 30,
    "participateCount": 20,
    "participate": false,
    "endTime": "2024-09-10 14:00",
    "vcontents": [
        {
            "contentId": 1,
            "content": "투표 항목1",
            "count": 12,
            "voted": false
        },
        {
            "contentId": 2,
            "content": "투표 항목2",
            "count": 3,
            "voted": false
        },
        {
            "contentId": 3,
            "content": "투표 항목3",
            "count": 5,
            "voted": false
        }
    ]
}
 */