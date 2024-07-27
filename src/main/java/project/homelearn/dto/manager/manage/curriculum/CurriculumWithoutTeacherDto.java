package project.homelearn.dto.manager.manage.curriculum;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import project.homelearn.entity.curriculum.CurriculumType;

import java.util.List;

@Data
public class CurriculumWithoutTeacherDto {

    @NotNull
    private CurriculumType type;

    private List<CurriculumIdAndThDto> curriculumIdAndThs;

    public CurriculumWithoutTeacherDto(CurriculumType type, List<CurriculumIdAndThDto> curriculumIdAndThs) {
        this.type = type;
        this.curriculumIdAndThs = curriculumIdAndThs;
    }
}

/*
List로 반환함
[
    {
        "type": "NCP",
        "curriculumIdAndThs": [
            {
                "id": 3,
                "th": 3
            }
        ]
    },
    {
        "type": "AWS",
        "curriculumIdAndThs": [
            {
                "id": 4,
                "th": 1
            },
            {
                "id": 5,
                "th": 2
            },
            {
                "id": 6,
                "th": 3
            }
        ]
    }
]
 */