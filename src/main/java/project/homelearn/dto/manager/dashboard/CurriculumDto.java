package project.homelearn.dto.manager.dashboard;

import lombok.Data;

@Data
public class CurriculumDto {

    private String name;

    private Integer th;

    private String teacherName;

    private Integer week;

    private Integer attendance;

    private Integer total;
}