package project.homelearn.dto.student.dashboard;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * project.homelearn.dto.student.dashboard.QViewMyBadgeDto is a Querydsl Projection type for ViewMyBadgeDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QViewMyBadgeDto extends ConstructorExpression<ViewMyBadgeDto> {

    private static final long serialVersionUID = 41578951L;

    public QViewMyBadgeDto(com.querydsl.core.types.Expression<Long> myBadgeId, com.querydsl.core.types.Expression<String> myBadgeName, com.querydsl.core.types.Expression<String> imageName, com.querydsl.core.types.Expression<String> imagePath, com.querydsl.core.types.Expression<java.time.LocalDate> getDate) {
        super(ViewMyBadgeDto.class, new Class<?>[]{long.class, String.class, String.class, String.class, java.time.LocalDate.class}, myBadgeId, myBadgeName, imageName, imagePath, getDate);
    }

}

