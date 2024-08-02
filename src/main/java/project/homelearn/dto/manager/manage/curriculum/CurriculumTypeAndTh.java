package project.homelearn.dto.manager.manage.curriculum;

import lombok.Data;
import project.homelearn.entity.curriculum.CurriculumType;

import java.util.List;

@Data
public class CurriculumTypeAndTh {

    private CurriculumType type;

    private List<Long> th;
}