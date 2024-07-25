package project.homelearn.dto.manager.inquiry;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import project.homelearn.entity.user.User;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ManagerInquiryDto {

    @NotNull
    private Long inquiryId;

    @NotBlank
    private String inquiryTitle;

    @NotBlank
    private String inquiryContent;

    @NotNull
    private LocalDateTime inquiryCreatedDate;

    @NotNull
    private User inquiryUser;

    private String response;

    private LocalDateTime responseDate;
}