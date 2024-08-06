package project.homelearn.dto.manager.dashboard;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurriculumDashboardDto {

    private Long id;

    private String name;

    private Long th;

    private String teacherName;

    private Integer attendance;

    private Integer total;
}