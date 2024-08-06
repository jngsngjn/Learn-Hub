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

 */