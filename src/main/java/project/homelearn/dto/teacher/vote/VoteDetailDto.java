package project.homelearn.dto.teacher.vote;

import lombok.Data;

import java.util.List;
import java.util.Map;

// response
@Data
public class VoteDetailDto {

    private Map<String, Long> voteCountByContent; // 항목별 투표 수

    private List<VoteDetailSub> voteDetailSubList;
}

/*
단일 선택 투표
{
    "voteCountByContent": {
        "투표 항목1": 1,
        "투표 항목3": 0,
        "투표 항목2": 0
    },
    "voteDetailSubList": [
        {
            "name": "정성진",
            "imagePath": null,
            "content": "투표 항목1"
        }
    ]
}

복수 선택 투표
{
    "voteCountByContent": {
        "투표 항목1": 1,
        "투표 항목3": 0,
        "투표 항목2": 1
    },
    "voteDetailSubList": [
        {
            "name": "정성진",
            "imagePath": null,
            "content": "투표 항목1"
        },
        {
            "name": "정성진",
            "imagePath": null,
            "content": "투표 항목2"
        }
    ]
}
 */