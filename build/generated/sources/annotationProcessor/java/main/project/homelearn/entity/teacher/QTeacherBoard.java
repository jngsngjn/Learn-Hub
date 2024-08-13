package project.homelearn.entity.teacher;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTeacherBoard is a Querydsl query type for TeacherBoard
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTeacherBoard extends EntityPathBase<TeacherBoard> {

    private static final long serialVersionUID = 2006287165L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTeacherBoard teacherBoard = new QTeacherBoard("teacherBoard");

    public final project.homelearn.entity.QBaseEntity _super = new project.homelearn.entity.QBaseEntity(this);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final project.homelearn.entity.curriculum.QCurriculum curriculum;

    public final BooleanPath emergency = createBoolean("emergency");

    public final StringPath filePath = createString("filePath");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final StringPath storeFileName = createString("storeFileName");

    public final StringPath title = createString("title");

    public final StringPath uploadFileName = createString("uploadFileName");

    public QTeacherBoard(String variable) {
        this(TeacherBoard.class, forVariable(variable), INITS);
    }

    public QTeacherBoard(Path<? extends TeacherBoard> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTeacherBoard(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTeacherBoard(PathMetadata metadata, PathInits inits) {
        this(TeacherBoard.class, metadata, inits);
    }

    public QTeacherBoard(Class<? extends TeacherBoard> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.curriculum = inits.isInitialized("curriculum") ? new project.homelearn.entity.curriculum.QCurriculum(forProperty("curriculum")) : null;
    }

}

