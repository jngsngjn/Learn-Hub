package project.homelearn.entity.homework;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStudentHomework is a Querydsl query type for StudentHomework
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStudentHomework extends EntityPathBase<StudentHomework> {

    private static final long serialVersionUID = -109495270L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStudentHomework studentHomework = new QStudentHomework("studentHomework");

    public final project.homelearn.entity.QBaseEntity _super = new project.homelearn.entity.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final StringPath description = createString("description");

    public final StringPath filePath = createString("filePath");

    public final QHomework homework;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final StringPath response = createString("response");

    public final DateTimePath<java.time.LocalDateTime> responseDate = createDateTime("responseDate", java.time.LocalDateTime.class);

    public final StringPath storeFileName = createString("storeFileName");

    public final StringPath uploadFileName = createString("uploadFileName");

    public final project.homelearn.entity.user.QUser user;

    public QStudentHomework(String variable) {
        this(StudentHomework.class, forVariable(variable), INITS);
    }

    public QStudentHomework(Path<? extends StudentHomework> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStudentHomework(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStudentHomework(PathMetadata metadata, PathInits inits) {
        this(StudentHomework.class, metadata, inits);
    }

    public QStudentHomework(Class<? extends StudentHomework> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.homework = inits.isInitialized("homework") ? new QHomework(forProperty("homework"), inits.get("homework")) : null;
        this.user = inits.isInitialized("user") ? new project.homelearn.entity.user.QUser(forProperty("user"), inits.get("user")) : null;
    }

}

