package project.homelearn.dto.common.inquiry;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class InquiryWriteDto {

    @NotBlank
    private String title;

    @NotBlank
    private String content;
}

/*
{
    "title": "title",
    "content": "content"
}
 */