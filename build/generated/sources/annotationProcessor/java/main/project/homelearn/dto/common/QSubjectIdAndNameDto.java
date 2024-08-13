package project.homelearn.dto.common;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * project.homelearn.dto.common.QSubjectIdAndNameDto is a Querydsl Projection type for SubjectIdAndNameDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QSubjectIdAndNameDto extends ConstructorExpression<SubjectIdAndNameDto> {

    private static final long serialVersionUID = 701247118L;

    public QSubjectIdAndNameDto(com.querydsl.core.types.Expression<Long> subjectId, com.querydsl.core.types.Expression<String> name) {
        super(SubjectIdAndNameDto.class, new Class<?>[]{long.class, String.class}, subjectId, name);
    }

}

