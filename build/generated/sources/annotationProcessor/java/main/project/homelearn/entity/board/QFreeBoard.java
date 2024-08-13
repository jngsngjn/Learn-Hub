package project.homelearn.entity.board;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFreeBoard is a Querydsl query type for FreeBoard
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFreeBoard extends EntityPathBase<FreeBoard> {

    private static final long serialVersionUID = 1504810533L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFreeBoard freeBoard = new QFreeBoard("freeBoard");

    public final QBoardBaseEntity _super;

    //inherited
    public final NumberPath<Integer> commentCount;

    public final ListPath<project.homelearn.entity.board.comment.FreeBoardComment, project.homelearn.entity.board.comment.QFreeBoardComment> comments = this.<project.homelearn.entity.board.comment.FreeBoardComment, project.homelearn.entity.board.comment.QFreeBoardComment>createList("comments", project.homelearn.entity.board.comment.FreeBoardComment.class, project.homelearn.entity.board.comment.QFreeBoardComment.class, PathInits.DIRECT2);

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

    public final ListPath<project.homelearn.entity.notification.student.StudentNotification, project.homelearn.entity.notification.student.QStudentNotification> studentNotifications = this.<project.homelearn.entity.notification.student.StudentNotification, project.homelearn.entity.notification.student.QStudentNotification>createList("studentNotifications", project.homelearn.entity.notification.student.StudentNotification.class, project.homelearn.entity.notification.student.QStudentNotification.class, PathInits.DIRECT2);

    //inherited
    public final StringPath title;

    // inherited
    public final project.homelearn.entity.user.QUser user;

    //inherited
    public final NumberPath<Integer> viewCount;

    public QFreeBoard(String variable) {
        this(FreeBoard.class, forVariable(variable), INITS);
    }

    public QFreeBoard(Path<? extends FreeBoard> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFreeBoard(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFreeBoard(PathMetadata metadata, PathInits inits) {
        this(FreeBoard.class, metadata, inits);
    }

    public QFreeBoard(Class<? extends FreeBoard> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new QBoardBaseEntity(type, metadata, inits);
        this.commentCount = _super.commentCount;
        this.content = _super.content;
        this.createdDate = _super.createdDate;
        this.imageName = _super.imageName;
        this.imagePath = _super.imagePath;
        this.modifiedDate = _super.modifiedDate;
        this.title = _super.title;
        this.user = _super.user;
        this.viewCount = _super.viewCount;
    }

}

