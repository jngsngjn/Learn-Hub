package project.homelearn.entity.board.comment;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QQuestionBoardComment is a Querydsl query type for QuestionBoardComment
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QQuestionBoardComment extends EntityPathBase<QuestionBoardComment> {

    private static final long serialVersionUID = -1956289629L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QQuestionBoardComment questionBoardComment = new QQuestionBoardComment("questionBoardComment");

    public final QCommentBaseEntity _super;

    //inherited
    public final StringPath content;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate;

    public final QQuestionBoardComment parentComment;

    public final project.homelearn.entity.board.QQuestionBoard questionBoard;

    public final ListPath<QuestionBoardComment, QQuestionBoardComment> replies = this.<QuestionBoardComment, QQuestionBoardComment>createList("replies", QuestionBoardComment.class, QQuestionBoardComment.class, PathInits.DIRECT2);

    public final ListPath<project.homelearn.entity.notification.student.StudentNotification, project.homelearn.entity.notification.student.QStudentNotification> studentNotifications = this.<project.homelearn.entity.notification.student.StudentNotification, project.homelearn.entity.notification.student.QStudentNotification>createList("studentNotifications", project.homelearn.entity.notification.student.StudentNotification.class, project.homelearn.entity.notification.student.QStudentNotification.class, PathInits.DIRECT2);

    public final ListPath<project.homelearn.entity.notification.teacher.TeacherNotification, project.homelearn.entity.notification.teacher.QTeacherNotification> teacherNotifications = this.<project.homelearn.entity.notification.teacher.TeacherNotification, project.homelearn.entity.notification.teacher.QTeacherNotification>createList("teacherNotifications", project.homelearn.entity.notification.teacher.TeacherNotification.class, project.homelearn.entity.notification.teacher.QTeacherNotification.class, PathInits.DIRECT2);

    // inherited
    public final project.homelearn.entity.user.QUser user;

    public QQuestionBoardComment(String variable) {
        this(QuestionBoardComment.class, forVariable(variable), INITS);
    }

    public QQuestionBoardComment(Path<? extends QuestionBoardComment> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QQuestionBoardComment(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QQuestionBoardComment(PathMetadata metadata, PathInits inits) {
        this(QuestionBoardComment.class, metadata, inits);
    }

    public QQuestionBoardComment(Class<? extends QuestionBoardComment> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new QCommentBaseEntity(type, metadata, inits);
        this.content = _super.content;
        this.createdDate = _super.createdDate;
        this.modifiedDate = _super.modifiedDate;
        this.parentComment = inits.isInitialized("parentComment") ? new QQuestionBoardComment(forProperty("parentComment"), inits.get("parentComment")) : null;
        this.questionBoard = inits.isInitialized("questionBoard") ? new project.homelearn.entity.board.QQuestionBoard(forProperty("questionBoard"), inits.get("questionBoard")) : null;
        this.user = _super.user;
    }

}

