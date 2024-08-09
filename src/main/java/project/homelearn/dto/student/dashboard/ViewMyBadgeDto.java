package project.homelearn.dto.student.dashboard;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.querydsl.core.annotations.QueryProjection;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ViewMyBadgeDto {

    @NotNull
    private Long myBadgeId;

    @NotBlank
    private String myBadgeName;

    @NotBlank
    private String imageName;

    @NotBlank
    private String imagePath;

    @NotBlank
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate getDate;

    @QueryProjection
    public ViewMyBadgeDto(Long myBadgeId, String myBadgeName, String imageName, String imagePath, LocalDate getDate) {
        this.myBadgeId = myBadgeId;
        this.myBadgeName = myBadgeName;
        this.imageName = imageName;
        this.imagePath = imagePath;
        this.getDate = getDate;
    }
}
