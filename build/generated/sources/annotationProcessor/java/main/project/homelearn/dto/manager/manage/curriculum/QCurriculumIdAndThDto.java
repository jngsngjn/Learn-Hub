package project.homelearn.dto.manager.manage.curriculum;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * project.homelearn.dto.manager.manage.curriculum.QCurriculumIdAndThDto is a Querydsl Projection type for CurriculumIdAndThDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QCurriculumIdAndThDto extends ConstructorExpression<CurriculumIdAndThDto> {

    private static final long serialVersionUID = -1959012542L;

    public QCurriculumIdAndThDto(com.querydsl.core.types.Expression<Long> id, com.querydsl.core.types.Expression<Long> th) {
        super(CurriculumIdAndThDto.class, new Class<?>[]{long.class, long.class}, id, th);
    }

}

