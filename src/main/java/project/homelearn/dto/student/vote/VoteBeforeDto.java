package project.homelearn.dto.student.vote;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class VoteBeforeDto {

    private Long voteId;
    private String title;
    private String description;

    private boolean isMultiple;
    private boolean isAnonymous;
    private boolean isFinished;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime endTime;

    private Long participantCount; // 총 투표 참여 수

    private List<VoteBeforeSub> voteBeforeSubs;
}