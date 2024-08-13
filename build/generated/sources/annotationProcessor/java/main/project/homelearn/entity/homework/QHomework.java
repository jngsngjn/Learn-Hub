package project.homelearn.entity.homework;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QHomework is a Querydsl query type for Homework
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QHomework extends EntityPathBase<Homework> {

    private static final long serialVersionUID = -1864740095L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QHomework homework = new QHomework("homework");

    public final project.homelearn.entity.QBaseEntity _super = new project.homelearn.entity.QBaseEntity(this);

    public final EnumPath<AcceptFile> acceptFile = createEnum("acceptFile", AcceptFile.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final project.homelearn.entity.curriculum.QCurriculum curriculum;

    public final DateTimePath<java.time.LocalDateTime> deadline = createDateTime("deadline", java.time.LocalDateTime.class);

    public final StringPath description = createString("description");

    public final StringPath filePath = createString("filePath");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final BooleanPath requiredFile = createBoolean("requiredFile");

    public final StringPath storeFileName = createString("storeFileName");

    public final ListPath<StudentHomework, QStudentHomework> studentHomeworks = this.<StudentHomework, QStudentHomework>createList("studentHomeworks", StudentHomework.class, QStudentHomework.class, PathInits.DIRECT2);

    public final ListPath<project.homelearn.entity.notification.student.StudentNotification, project.homelearn.entity.notification.student.QStudentNotification> studentNotifications = this.<project.homelearn.entity.notification.student.StudentNotification, project.homelearn.entity.notification.student.QStudentNotification>createList("studentNotifications", project.homelearn.entity.notification.student.StudentNotification.class, project.homelearn.entity.notification.student.QStudentNotification.class, PathInits.DIRECT2);

    public final StringPath title = createString("title");

    public final StringPath uploadFileName = createString("uploadFileName");

    public QHomework(String variable) {
        this(Homework.class, forVariable(variable), INITS);
    }

    public QHomework(Path<? extends Homework> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QHomework(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QHomework(PathMetadata metadata, PathInits inits) {
        this(Homework.class, metadata, inits);
    }

    public QHomework(Class<? extends Homework> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.curriculum = inits.isInitialized("curriculum") ? new project.homelearn.entity.curriculum.QCurriculum(forProperty("curriculum")) : null;
    }

}

