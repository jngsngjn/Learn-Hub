package project.homelearn.entity.board;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QQuestionBoard is a Querydsl query type for QuestionBoard
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QQuestionBoard extends EntityPathBase<QuestionBoard> {

    private static final long serialVersionUID = 1911140203L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QQuestionBoard questionBoard = new QQuestionBoard("questionBoard");

    public final QBoardBaseEntity _super;

    //inherited
    public final NumberPath<Integer> commentCount;

    public final ListPath<project.homelearn.entity.board.comment.QuestionBoardComment, project.homelearn.entity.board.comment.QQuestionBoardComment> comments = this.<project.homelearn.entity.board.comment.QuestionBoardComment, project.homelearn.entity.board.comment.QQuestionBoardComment>createList("comments", project.homelearn.entity.board.comment.QuestionBoardComment.class, project.homelearn.entity.board.comment.QQuestionBoardComment.class, PathInits.DIRECT2);

    //inherited
    public final StringPath content;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final StringPath imageName;

    //inherited
    public final StringPath imagePath;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate;

    public final ListPath<project.homelearn.entity.board.scrap.QuestionScrap, project.homelearn.entity.board.scrap.QQuestionScrap> questionScraps = this.<project.homelearn.entity.board.scrap.QuestionScrap, project.homelearn.entity.board.scrap.QQuestionScrap>createList("questionScraps", project.homelearn.entity.board.scrap.QuestionScrap.class, project.homelearn.entity.board.scrap.QQuestionScrap.class, PathInits.DIRECT2);

    public final ListPath<project.homelearn.entity.notification.student.StudentNotification, project.homelearn.entity.notification.student.QStudentNotification> studentNotifications = this.<project.homelearn.entity.notification.student.StudentNotification, project.homelearn.entity.notification.student.QStudentNotification>createList("studentNotifications", project.homelearn.entity.notification.student.StudentNotification.class, project.homelearn.entity.notification.student.QStudentNotification.class, PathInits.DIRECT2);

    public final project.homelearn.entity.curriculum.QSubject subject;

    public final ListPath<project.homelearn.entity.notification.teacher.TeacherNotification, project.homelearn.entity.notification.teacher.QTeacherNotification> teacherNotifications = this.<project.homelearn.entity.notification.teacher.TeacherNotification, project.homelearn.entity.notification.teacher.QTeacherNotification>createList("teacherNotifications", project.homelearn.entity.notification.teacher.TeacherNotification.class, project.homelearn.entity.notification.teacher.QTeacherNotification.class, PathInits.DIRECT2);

    //inherited
    public final StringPath title;

    // inherited
    public final project.homelearn.entity.user.QUser user;

    //inherited
    public final NumberPath<Integer> viewCount;

    public QQuestionBoard(String variable) {
        this(QuestionBoard.class, forVariable(variable), INITS);
    }

    public QQuestionBoard(Path<? extends QuestionBoard> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QQuestionBoard(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QQuestionBoard(PathMetadata metadata, PathInits inits) {
        this(QuestionBoard.class, metadata, inits);
    }

    public QQuestionBoard(Class<? extends QuestionBoard> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new QBoardBaseEntity(type, metadata, inits);
        this.commentCount = _super.commentCount;
        this.content = _super.content;
        this.createdDate = _super.createdDate;
        this.imageName = _super.imageName;
        this.imagePath = _super.imagePath;
        this.modifiedDate = _super.modifiedDate;
        this.subject = inits.isInitialized("subject") ? new project.homelearn.entity.curriculum.QSubject(forProperty("subject"), inits.get("subject")) : null;
        this.title = _super.title;
        this.user = _super.user;
        this.viewCount = _super.viewCount;
    }

}

