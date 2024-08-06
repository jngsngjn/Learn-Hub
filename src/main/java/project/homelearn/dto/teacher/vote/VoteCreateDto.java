package project.homelearn.dto.teacher.vote;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class VoteCreateDto {

    @NotBlank
    private String title;

    private String description;

    @NotNull
    private Boolean isAnonymous;

    @NotNull
    private Boolean isMultipleChoice;

    @Future
    @NotNull
    private LocalDateTime endTime;

    @NotEmpty
    @Size(min = 2)
    private List<String> contents;
}

/*
{
    "title": "투표",
    "description": "투표 테스트",
    "isAnonymous": true,
    "isMultipleChoice": true,
    "endTime": "2024-08-06T14:00:00",
    "contents": ["투표 항목1", "투표 항목2", "투표 항목3", "투표 항목4"]
}
 */