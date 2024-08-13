package project.homelearn.dto.teacher.dashboard;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * project.homelearn.dto.teacher.dashboard.QQuestionTop5Dto is a Querydsl Projection type for QuestionTop5Dto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QQuestionTop5Dto extends ConstructorExpression<QuestionTop5Dto> {

    private static final long serialVersionUID = -170718362L;

    public QQuestionTop5Dto(com.querydsl.core.types.Expression<Long> questionId, com.querydsl.core.types.Expression<String> subjectName, com.querydsl.core.types.Expression<String> title, com.querydsl.core.types.Expression<String> content, com.querydsl.core.types.Expression<java.time.LocalDateTime> createdDate) {
        super(QuestionTop5Dto.class, new Class<?>[]{long.class, String.class, String.class, String.class, java.time.LocalDateTime.class}, questionId, subjectName, title, content, createdDate);
    }

}

