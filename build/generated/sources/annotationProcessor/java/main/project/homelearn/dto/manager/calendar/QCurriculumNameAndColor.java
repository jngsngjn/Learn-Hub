package project.homelearn.dto.manager.calendar;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * project.homelearn.dto.manager.calendar.QCurriculumNameAndColor is a Querydsl Projection type for CurriculumNameAndColor
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QCurriculumNameAndColor extends ConstructorExpression<CurriculumNameAndColor> {

    private static final long serialVersionUID = -1226081260L;

    public QCurriculumNameAndColor(com.querydsl.core.types.Expression<String> name, com.querydsl.core.types.Expression<String> color) {
        super(CurriculumNameAndColor.class, new Class<?>[]{String.class, String.class}, name, color);
    }

}

