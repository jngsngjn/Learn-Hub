package project.homelearn.dto.teacher.subject;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * project.homelearn.dto.teacher.subject.QSubjectBoardViewDto is a Querydsl Projection type for SubjectBoardViewDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QSubjectBoardViewDto extends ConstructorExpression<SubjectBoardViewDto> {

    private static final long serialVersionUID = 813194821L;

    public QSubjectBoardViewDto(com.querydsl.core.types.Expression<Long> boardId, com.querydsl.core.types.Expression<String> title, com.querydsl.core.types.Expression<String> content, com.querydsl.core.types.Expression<Integer> viewCount, com.querydsl.core.types.Expression<String> filePath, com.querydsl.core.types.Expression<String> fileName, com.querydsl.core.types.Expression<java.time.LocalDateTime> writeDate) {
        super(SubjectBoardViewDto.class, new Class<?>[]{long.class, String.class, String.class, int.class, String.class, String.class, java.time.LocalDateTime.class}, boardId, title, content, viewCount, filePath, fileName, writeDate);
    }

}

