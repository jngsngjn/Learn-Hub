package project.homelearn.dto.teacher.vote;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

// response
@Data
public class VoteBasicDto {

    private Long voteId;
    private String title;
    private String description;

    private boolean isMultiple;
    private boolean isAnonymous;

    private Long total; // 총 학생 수
    private Long participantCount; // 총 투표 참여 수

    private List<String> contents; // 투표 항목
    private List<Long> contentVoteCount; // 항목별 투표 수

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime endTime;
}