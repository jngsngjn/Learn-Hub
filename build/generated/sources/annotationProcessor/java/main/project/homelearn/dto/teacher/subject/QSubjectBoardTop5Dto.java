package project.homelearn.dto.teacher.subject;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * project.homelearn.dto.teacher.subject.QSubjectBoardTop5Dto is a Querydsl Projection type for SubjectBoardTop5Dto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QSubjectBoardTop5Dto extends ConstructorExpression<SubjectBoardTop5Dto> {

    private static final long serialVersionUID = -781845110L;

    public QSubjectBoardTop5Dto(com.querydsl.core.types.Expression<Long> boardId, com.querydsl.core.types.Expression<String> title, com.querydsl.core.types.Expression<String> content, com.querydsl.core.types.Expression<String> fileName, com.querydsl.core.types.Expression<java.time.LocalDateTime> writeDate) {
        super(SubjectBoardTop5Dto.class, new Class<?>[]{long.class, String.class, String.class, String.class, java.time.LocalDateTime.class}, boardId, title, content, fileName, writeDate);
    }

}

