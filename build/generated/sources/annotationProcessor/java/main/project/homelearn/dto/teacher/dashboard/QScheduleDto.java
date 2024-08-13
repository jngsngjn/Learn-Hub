package project.homelearn.dto.teacher.dashboard;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * project.homelearn.dto.teacher.dashboard.QScheduleDto is a Querydsl Projection type for ScheduleDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QScheduleDto extends ConstructorExpression<ScheduleDto> {

    private static final long serialVersionUID = 45639733L;

    public QScheduleDto(com.querydsl.core.types.Expression<Long> id, com.querydsl.core.types.Expression<String> title, com.querydsl.core.types.Expression<java.time.LocalDate> startDate, com.querydsl.core.types.Expression<java.time.LocalDate> endDate) {
        super(ScheduleDto.class, new Class<?>[]{long.class, String.class, java.time.LocalDate.class, java.time.LocalDate.class}, id, title, startDate, endDate);
    }

}

