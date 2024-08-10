package project.homelearn.dto.teacher.inquiry;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class TeacherInquiryListToManagerDto {

    @NotNull
    private Long inquiryId;

    @NotBlank
    private String title;

    @NotBlank
    private String name;

    @NotBlank
    private String curriculumName;

    @NotNull
    private Long curriculumTh;

    @NotBlank
    private LocalDateTime createDate;

    private String response;
}
