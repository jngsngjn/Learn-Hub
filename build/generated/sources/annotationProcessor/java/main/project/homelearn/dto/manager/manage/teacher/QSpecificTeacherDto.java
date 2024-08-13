package project.homelearn.dto.manager.manage.teacher;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * project.homelearn.dto.manager.manage.teacher.QSpecificTeacherDto is a Querydsl Projection type for SpecificTeacherDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QSpecificTeacherDto extends ConstructorExpression<SpecificTeacherDto> {

    private static final long serialVersionUID = -2012444490L;

    public QSpecificTeacherDto(com.querydsl.core.types.Expression<Long> id, com.querydsl.core.types.Expression<String> name, com.querydsl.core.types.Expression<String> email, com.querydsl.core.types.Expression<String> phone, com.querydsl.core.types.Expression<Long> curriculumId, com.querydsl.core.types.Expression<project.homelearn.entity.curriculum.CurriculumType> type, com.querydsl.core.types.Expression<Long> th) {
        super(SpecificTeacherDto.class, new Class<?>[]{long.class, String.class, String.class, String.class, long.class, project.homelearn.entity.curriculum.CurriculumType.class, long.class}, id, name, email, phone, curriculumId, type, th);
    }

}

