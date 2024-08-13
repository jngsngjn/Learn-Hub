package project.homelearn.dto.student.dashboard;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * project.homelearn.dto.student.dashboard.QViewLectureDto is a Querydsl Projection type for ViewLectureDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QViewLectureDto extends ConstructorExpression<ViewLectureDto> {

    private static final long serialVersionUID = -409802688L;

    public QViewLectureDto(com.querydsl.core.types.Expression<Long> lectureId, com.querydsl.core.types.Expression<String> youtubeUrl) {
        super(ViewLectureDto.class, new Class<?>[]{long.class, String.class}, lectureId, youtubeUrl);
    }

}

