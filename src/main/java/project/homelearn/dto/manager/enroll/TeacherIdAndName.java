package project.homelearn.dto.manager.enroll;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class TeacherIdAndName {

    private Long teacherId;
    private String teacherName;

    @QueryProjection
    public TeacherIdAndName(Long teacherId, String teacherName) {
        this.teacherId = teacherId;
        this.teacherName = teacherName;
    }
}