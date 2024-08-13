package project.homelearn.dto.student.dashboard;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * project.homelearn.dto.student.dashboard.QViewRecentStudyLectureDto is a Querydsl Projection type for ViewRecentStudyLectureDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QViewRecentStudyLectureDto extends ConstructorExpression<ViewRecentStudyLectureDto> {

    private static final long serialVersionUID = 1114659888L;

    public QViewRecentStudyLectureDto(com.querydsl.core.types.Expression<Long> lectureId, com.querydsl.core.types.Expression<String> subjectName, com.querydsl.core.types.Expression<String> title, com.querydsl.core.types.Expression<Long> lastPosition, com.querydsl.core.types.Expression<String> youtubeUrl) {
        super(ViewRecentStudyLectureDto.class, new Class<?>[]{long.class, String.class, String.class, long.class, String.class}, lectureId, subjectName, title, lastPosition, youtubeUrl);
    }

}

