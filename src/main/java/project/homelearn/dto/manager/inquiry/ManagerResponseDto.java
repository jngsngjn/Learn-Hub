package project.homelearn.dto.manager.inquiry;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ManagerResponseDto {

    @NotNull
    private String response;

    @NotNull
    private LocalDateTime responseDate;
}
