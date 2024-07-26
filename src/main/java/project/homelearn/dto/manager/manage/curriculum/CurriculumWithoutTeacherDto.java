package project.homelearn.dto.manager.manage.curriculum;

import lombok.Data;
import project.homelearn.entity.curriculum.CurriculumType;

import java.util.List;

@Data
public class CurriculumWithoutTeacherDto {

    private CurriculumType type;
    private List<CurriculumIdAndTh> curriculumIdAndThs;

    static class CurriculumIdAndTh {
        public Long id;
        public Long th;
    }
}