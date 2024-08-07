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

    private boolean isMultiple;
    private boolean isAnonymous;
    private boolean isFinished;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime endTime;

    private Long total; // 총 학생 수
    private Long participantCount; // 총 투표 참여 수

    private Map<String, Long> voteCountByContent; // 항목별 투표 수
}

/*

 */