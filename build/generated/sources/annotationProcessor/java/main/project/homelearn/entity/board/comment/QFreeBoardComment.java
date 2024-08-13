package project.homelearn.entity.board.comment;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFreeBoardComment is a Querydsl query type for FreeBoardComment
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFreeBoardComment extends EntityPathBase<FreeBoardComment> {

    private static final long serialVersionUID = 1776568105L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFreeBoardComment freeBoardComment = new QFreeBoardComment("freeBoardComment");

    public final QCommentBaseEntity _super;

    //inherited
    public final StringPath content;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate;

    public final project.homelearn.entity.board.QFreeBoard freeBoard;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate;

    public final QFreeBoardComment parentComment;

    public final ListPath<FreeBoardComment, QFreeBoardComment> replies = this.<FreeBoardComment, QFreeBoardComment>createList("replies", FreeBoardComment.class, QFreeBoardComment.class, PathInits.DIRECT2);

    public final ListPath<project.homelearn.entity.notification.student.StudentNotification, project.homelearn.entity.notification.student.QStudentNotification> studentNotifications = this.<project.homelearn.entity.notification.student.StudentNotification, project.homelearn.entity.notification.student.QStudentNotification>createList("studentNotifications", project.homelearn.entity.notification.student.StudentNotification.class, project.homelearn.entity.notification.student.QStudentNotification.class, PathInits.DIRECT2);

    // inherited
    public final project.homelearn.entity.user.QUser user;

    public QFreeBoardComment(String variable) {
        this(FreeBoardComment.class, forVariable(variable), INITS);
    }

    public QFreeBoardComment(Path<? extends FreeBoardComment> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFreeBoardComment(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFreeBoardComment(PathMetadata metadata, PathInits inits) {
        this(FreeBoardComment.class, metadata, inits);
    }

    public QFreeBoardComment(Class<? extends FreeBoardComment> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new QCommentBaseEntity(type, metadata, inits);
        this.content = _super.content;
        this.createdDate = _super.createdDate;
        this.freeBoard = inits.isInitialized("freeBoard") ? new project.homelearn.entity.board.QFreeBoard(forProperty("freeBoard"), inits.get("freeBoard")) : null;
        this.modifiedDate = _super.modifiedDate;
        this.parentComment = inits.isInitialized("parentComment") ? new QFreeBoardComment(forProperty("parentComment"), inits.get("parentComment")) : null;
        this.user = _super.user;
    }

}

