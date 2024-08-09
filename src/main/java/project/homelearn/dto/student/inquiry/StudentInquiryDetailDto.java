package project.homelearn.dto.student.inquiry;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class StudentInquiryDetailDto {

    @NotNull
    private Long inquiryId;

    @NotBlank
    private String name;

    @NotBlank
    private String curriculumName;

    @NotBlank
    private Long curriculumTh;

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @NotBlank
    private LocalDateTime createDate;

    private String response;
}
