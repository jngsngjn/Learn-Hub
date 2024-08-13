package project.homelearn.dto.student.dashboard;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * project.homelearn.dto.student.dashboard.QViewHomeworkDto is a Querydsl Projection type for ViewHomeworkDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QViewHomeworkDto extends ConstructorExpression<ViewHomeworkDto> {

    private static final long serialVersionUID = 1543936624L;

    public QViewHomeworkDto(com.querydsl.core.types.Expression<Long> homeworkId, com.querydsl.core.types.Expression<String> title, com.querydsl.core.types.Expression<String> description, com.querydsl.core.types.Expression<java.time.LocalDateTime> deadLine, com.querydsl.core.types.Expression<Boolean> isSubmit) {
        super(ViewHomeworkDto.class, new Class<?>[]{long.class, String.class, String.class, java.time.LocalDateTime.class, boolean.class}, homeworkId, title, description, deadLine, isSubmit);
    }

}

