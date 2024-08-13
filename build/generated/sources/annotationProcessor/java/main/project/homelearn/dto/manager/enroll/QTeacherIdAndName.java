package project.homelearn.dto.manager.enroll;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * project.homelearn.dto.manager.enroll.QTeacherIdAndName is a Querydsl Projection type for TeacherIdAndName
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QTeacherIdAndName extends ConstructorExpression<TeacherIdAndName> {

    private static final long serialVersionUID = -149764097L;

    public QTeacherIdAndName(com.querydsl.core.types.Expression<Long> teacherId, com.querydsl.core.types.Expression<String> teacherName) {
        super(TeacherIdAndName.class, new Class<?>[]{long.class, String.class}, teacherId, teacherName);
    }

}

