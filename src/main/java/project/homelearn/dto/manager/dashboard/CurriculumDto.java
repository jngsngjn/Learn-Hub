package project.homelearn.dto.manager.dashboard;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 미완성
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurriculumDto {

    private Long id;

    private String name;

    private Integer th;

    private String teacherName;

    private Integer attendance;

    private Integer total;
}