package project.homelearn.entity.calendar;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTeacherCalendar is a Querydsl query type for TeacherCalendar
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTeacherCalendar extends EntityPathBase<TeacherCalendar> {

    private static final long serialVersionUID = 703602781L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTeacherCalendar teacherCalendar = new QTeacherCalendar("teacherCalendar");

    public final DatePath<java.time.LocalDate> endDate = createDate("endDate", java.time.LocalDate.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DatePath<java.time.LocalDate> startDate = createDate("startDate", java.time.LocalDate.class);

    public final StringPath title = createString("title");

    public final project.homelearn.entity.user.QUser user;

    public QTeacherCalendar(String variable) {
        this(TeacherCalendar.class, forVariable(variable), INITS);
    }

    public QTeacherCalendar(Path<? extends TeacherCalendar> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTeacherCalendar(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTeacherCalendar(PathMetadata metadata, PathInits inits) {
        this(TeacherCalendar.class, metadata, inits);
    }

    public QTeacherCalendar(Class<? extends TeacherCalendar> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new project.homelearn.entity.user.QUser(forProperty("user"), inits.get("user")) : null;
    }

}

