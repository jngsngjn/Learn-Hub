package project.homelearn.dto.manager.dashboard;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * project.homelearn.dto.manager.dashboard.QManagerScheduleDto is a Querydsl Projection type for ManagerScheduleDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QManagerScheduleDto extends ConstructorExpression<ManagerScheduleDto> {

    private static final long serialVersionUID = -350740253L;

    public QManagerScheduleDto(com.querydsl.core.types.Expression<Long> id, com.querydsl.core.types.Expression<String> title, com.querydsl.core.types.Expression<java.time.LocalDate> startDate, com.querydsl.core.types.Expression<java.time.LocalDate> endDate, com.querydsl.core.types.Expression<String> color) {
        super(ManagerScheduleDto.class, new Class<?>[]{long.class, String.class, java.time.LocalDate.class, java.time.LocalDate.class, String.class}, id, title, startDate, endDate, color);
    }

}

