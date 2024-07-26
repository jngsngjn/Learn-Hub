package project.homelearn.dto.manager.manage.student;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import project.homelearn.entity.user.Gender;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

@Data
@AllArgsConstructor
public class StudentDetailsDto {

    @NotNull
    private Long studentId;

    @NotNull
    private String name;

    @NotNull
    private Long curriculumTh;

    @NotNull
    private String curriculumName;

    @NotNull
    private String phone;

    @NotNull
    private Gender gender;

    @NotNull
    private String email;

    @NotNull
    private double attendanceRate;

    @NotNull
    private Map<LocalDate, String> attendanceStatus; //날짜별 출석여부 (출석, 지각, 결석)

    @NotNull
    private int unsolvedInquiryCount;

    @NotNull
    private String inquiryPageUrl;
}
