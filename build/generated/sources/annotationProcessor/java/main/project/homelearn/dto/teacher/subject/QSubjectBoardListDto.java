package project.homelearn.dto.teacher.subject;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * project.homelearn.dto.teacher.subject.QSubjectBoardListDto is a Querydsl Projection type for SubjectBoardListDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QSubjectBoardListDto extends ConstructorExpression<SubjectBoardListDto> {

    private static final long serialVersionUID = 540932524L;

    public QSubjectBoardListDto(com.querydsl.core.types.Expression<Long> boardId, com.querydsl.core.types.Expression<String> title, com.querydsl.core.types.Expression<java.time.LocalDateTime> writeDate, com.querydsl.core.types.Expression<Integer> viewCount) {
        super(SubjectBoardListDto.class, new Class<?>[]{long.class, String.class, java.time.LocalDateTime.class, int.class}, boardId, title, writeDate, viewCount);
    }

}

