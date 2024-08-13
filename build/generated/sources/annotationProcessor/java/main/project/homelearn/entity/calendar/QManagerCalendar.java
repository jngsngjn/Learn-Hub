package project.homelearn.entity.calendar;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QManagerCalendar is a Querydsl query type for ManagerCalendar
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QManagerCalendar extends EntityPathBase<ManagerCalendar> {

    private static final long serialVersionUID = -1512543960L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QManagerCalendar managerCalendar = new QManagerCalendar("managerCalendar");

    public final project.homelearn.entity.curriculum.QCurriculum curriculum;

    public final DatePath<java.time.LocalDate> endDate = createDate("endDate", java.time.LocalDate.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DatePath<java.time.LocalDate> startDate = createDate("startDate", java.time.LocalDate.class);

    public final StringPath title = createString("title");

    public QManagerCalendar(String variable) {
        this(ManagerCalendar.class, forVariable(variable), INITS);
    }

    public QManagerCalendar(Path<? extends ManagerCalendar> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QManagerCalendar(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QManagerCalendar(PathMetadata metadata, PathInits inits) {
        this(ManagerCalendar.class, metadata, inits);
    }

    public QManagerCalendar(Class<? extends ManagerCalendar> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.curriculum = inits.isInitialized("curriculum") ? new project.homelearn.entity.curriculum.QCurriculum(forProperty("curriculum")) : null;
    }

}

