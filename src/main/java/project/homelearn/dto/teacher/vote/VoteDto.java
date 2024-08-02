package project.homelearn.dto.teacher.vote;

import lombok.Data;
import project.homelearn.entity.vote.VoteContent;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class VoteDto {

    private String title;
    private String description;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private List<String> contents;
}

/*

 */