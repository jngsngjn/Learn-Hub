package project.homelearn.dto.teacher.vote;

import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class VoteCreateDto {

    private String title;
    private String description;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @Size(min = 2)
    private List<String> contents;
}

/*

 */