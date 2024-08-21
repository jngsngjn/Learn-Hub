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
    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @NotNull
    private LocalDateTime createdDate;

    @NotNull
    private Long userId;

    private String response;

    private LocalDateTime responseDate;
}