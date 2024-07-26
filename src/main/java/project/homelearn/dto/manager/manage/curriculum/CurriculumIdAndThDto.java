package project.homelearn.dto.manager.manage.curriculum;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class CurriculumIdAndThDto {

    public Long id;
    public Long th;

    @QueryProjection
    public CurriculumIdAndThDto(Long id, Long th) {
        this.id = id;
        this.th = th;
    }
}