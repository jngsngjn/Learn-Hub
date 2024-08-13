package project.homelearn.entity.curriculum;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSubject is a Querydsl query type for Subject
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSubject extends EntityPathBase<Subject> {

    private static final long serialVersionUID = 1008403974L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSubject subject = new QSubject("subject");

    public final project.homelearn.entity.QBaseEntity _super = new project.homelearn.entity.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final QCurriculum curriculum;

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imageName = createString("imageName");

    public final StringPath imagePath = createString("imagePath");

    public final ListPath<Lecture, QLecture> lectures = this.<Lecture, QLecture>createList("lectures", Lecture.class, QLecture.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final StringPath name = createString("name");

    public final ListPath<project.homelearn.entity.board.QuestionBoard, project.homelearn.entity.board.QQuestionBoard> questionBoards = this.<project.homelearn.entity.board.QuestionBoard, project.homelearn.entity.board.QQuestionBoard>createList("questionBoards", project.homelearn.entity.board.QuestionBoard.class, project.homelearn.entity.board.QQuestionBoard.class, PathInits.DIRECT2);

    public final ListPath<SubjectBoard, QSubjectBoard> subjectBoards = this.<SubjectBoard, QSubjectBoard>createList("subjectBoards", SubjectBoard.class, QSubjectBoard.class, PathInits.DIRECT2);

    public QSubject(String variable) {
        this(Subject.class, forVariable(variable), INITS);
    }

    public QSubject(Path<? extends Subject> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSubject(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSubject(PathMetadata metadata, PathInits inits) {
        this(Subject.class, metadata, inits);
    }

    public QSubject(Class<? extends Subject> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.curriculum = inits.isInitialized("curriculum") ? new QCurriculum(forProperty("curriculum")) : null;
    }

}

