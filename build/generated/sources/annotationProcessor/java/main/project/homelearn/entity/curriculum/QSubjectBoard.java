package project.homelearn.entity.curriculum;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSubjectBoard is a Querydsl query type for SubjectBoard
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSubjectBoard extends EntityPathBase<SubjectBoard> {

    private static final long serialVersionUID = 1743506560L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSubjectBoard subjectBoard = new QSubjectBoard("subjectBoard");

    public final project.homelearn.entity.QBaseEntity _super = new project.homelearn.entity.QBaseEntity(this);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final StringPath filePath = createString("filePath");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final StringPath storeFileName = createString("storeFileName");

    public final QSubject subject;

    public final StringPath title = createString("title");

    public final StringPath uploadFileName = createString("uploadFileName");

    public final NumberPath<Integer> viewCount = createNumber("viewCount", Integer.class);

    public QSubjectBoard(String variable) {
        this(SubjectBoard.class, forVariable(variable), INITS);
    }

    public QSubjectBoard(Path<? extends SubjectBoard> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSubjectBoard(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSubjectBoard(PathMetadata metadata, PathInits inits) {
        this(SubjectBoard.class, metadata, inits);
    }

    public QSubjectBoard(Class<? extends SubjectBoard> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.subject = inits.isInitialized("subject") ? new QSubject(forProperty("subject"), inits.get("subject")) : null;
    }

}

