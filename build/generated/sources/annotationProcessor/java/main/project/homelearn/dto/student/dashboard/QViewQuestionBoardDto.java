package project.homelearn.dto.student.dashboard;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * project.homelearn.dto.student.dashboard.QViewQuestionBoardDto is a Querydsl Projection type for ViewQuestionBoardDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QViewQuestionBoardDto extends ConstructorExpression<ViewQuestionBoardDto> {

    private static final long serialVersionUID = 679034014L;

    public QViewQuestionBoardDto(com.querydsl.core.types.Expression<Long> questionId, com.querydsl.core.types.Expression<String> subjectName, com.querydsl.core.types.Expression<String> title, com.querydsl.core.types.Expression<String> content, com.querydsl.core.types.Expression<java.time.LocalDateTime> createdDate) {
        super(ViewQuestionBoardDto.class, new Class<?>[]{long.class, String.class, String.class, String.class, java.time.LocalDateTime.class}, questionId, subjectName, title, content, createdDate);
    }

}

