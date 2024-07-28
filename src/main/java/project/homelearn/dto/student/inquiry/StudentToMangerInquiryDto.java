package project.homelearn.dto.student.inquiry;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class StudentToMangerInquiryDto {

    @NotBlank
    private String title;

    @NotBlank
    private String content;
}

/*
{
    "title": "제목",
    "content": "본문"
}
 */