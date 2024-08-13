package project.homelearn.dto.teacher.lecture;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * project.homelearn.dto.teacher.lecture.QLectureListDto is a Querydsl Projection type for LectureListDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QLectureListDto extends ConstructorExpression<LectureListDto> {

    private static final long serialVersionUID = 1596832652L;

    public QLectureListDto(com.querydsl.core.types.Expression<Long> lectureId, com.querydsl.core.types.Expression<String> title, com.querydsl.core.types.Expression<String> link, com.querydsl.core.types.Expression<Long> subjectId) {
        super(LectureListDto.class, new Class<?>[]{long.class, String.class, String.class, long.class}, lectureId, title, link, subjectId);
    }

    public QLectureListDto(com.querydsl.core.types.Expression<Long> lectureId, com.querydsl.core.types.Expression<String> title, com.querydsl.core.types.Expression<String> link) {
        super(LectureListDto.class, new Class<?>[]{long.class, String.class, String.class}, lectureId, title, link);
    }

}

