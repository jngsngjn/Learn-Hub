package project.homelearn.dto.teacher.vote;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

// page response
@Data
public class VoteTabDto {

    private Long voteId;

    private String title;

    private String description;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime endTime;

    private Long voteCount;
}

/*

 */