package project.homelearn.entity.curriculum;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStudentLecture is a Querydsl query type for StudentLecture
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStudentLecture extends EntityPathBase<StudentLecture> {

    private static final long serialVersionUID = -1586052951L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStudentLecture studentLecture = new QStudentLecture("studentLecture");

    public final DateTimePath<java.time.LocalDateTime> completedDate = createDateTime("completedDate", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.time.LocalDateTime> initialViewDate = createDateTime("initialViewDate", java.time.LocalDateTime.class);

    public final BooleanPath isCompleted = createBoolean("isCompleted");

    public final NumberPath<Long> lastPosition = createNumber("lastPosition", Long.class);

    public final QLecture lecture;

    public final project.homelearn.entity.user.QUser user;

    public QStudentLecture(String variable) {
        this(StudentLecture.class, forVariable(variable), INITS);
    }

    public QStudentLecture(Path<? extends StudentLecture> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStudentLecture(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStudentLecture(PathMetadata metadata, PathInits inits) {
        this(StudentLecture.class, metadata, inits);
    }

    public QStudentLecture(Class<? extends StudentLecture> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.lecture = inits.isInitialized("lecture") ? new QLecture(forProperty("lecture"), inits.get("lecture")) : null;
        this.user = inits.isInitialized("user") ? new project.homelearn.entity.user.QUser(forProperty("user"), inits.get("user")) : null;
    }

}

