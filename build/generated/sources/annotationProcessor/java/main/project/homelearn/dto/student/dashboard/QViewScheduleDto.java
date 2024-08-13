package project.homelearn.dto.student.dashboard;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * project.homelearn.dto.student.dashboard.QViewScheduleDto is a Querydsl Projection type for ViewScheduleDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QViewScheduleDto extends ConstructorExpression<ViewScheduleDto> {

    private static final long serialVersionUID = -2048772375L;

    public QViewScheduleDto(com.querydsl.core.types.Expression<Long> id, com.querydsl.core.types.Expression<String> title, com.querydsl.core.types.Expression<java.time.LocalDate> startDate, com.querydsl.core.types.Expression<java.time.LocalDate> endDate) {
        super(ViewScheduleDto.class, new Class<?>[]{long.class, String.class, java.time.LocalDate.class, java.time.LocalDate.class}, id, title, startDate, endDate);
    }

}

