package project.homelearn.dto.manager.manage.curriculum;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * project.homelearn.dto.manager.manage.curriculum.QCurriculumTeacherDto is a Querydsl Projection type for CurriculumTeacherDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QCurriculumTeacherDto extends ConstructorExpression<CurriculumTeacherDto> {

    private static final long serialVersionUID = 1555083376L;

    public QCurriculumTeacherDto(com.querydsl.core.types.Expression<String> name, com.querydsl.core.types.Expression<String> email, com.querydsl.core.types.Expression<String> phone) {
        super(CurriculumTeacherDto.class, new Class<?>[]{String.class, String.class, String.class}, name, email, phone);
    }

}

