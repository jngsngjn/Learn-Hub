package project.homelearn.entity.notification.teacher;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTeacherNotification is a Querydsl query type for TeacherNotification
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTeacherNotification extends EntityPathBase<TeacherNotification> {

    private static final long serialVersionUID = 935329579L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTeacherNotification teacherNotification = new QTeacherNotification("teacherNotification");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final project.homelearn.entity.inquiry.QManagerInquiry managerInquiry;

    public final project.homelearn.entity.board.QQuestionBoard questionBoard;

    public final project.homelearn.entity.board.comment.QQuestionBoardComment questionBoardComment;

    public final project.homelearn.entity.inquiry.QTeacherInquiry teacherInquiry;

    public final EnumPath<TeacherNotificationType> type = createEnum("type", TeacherNotificationType.class);

    public final project.homelearn.entity.user.QUser user;

    public QTeacherNotification(String variable) {
        this(TeacherNotification.class, forVariable(variable), INITS);
    }

    public QTeacherNotification(Path<? extends TeacherNotification> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTeacherNotification(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTeacherNotification(PathMetadata metadata, PathInits inits) {
        this(TeacherNotification.class, metadata, inits);
    }

    public QTeacherNotification(Class<? extends TeacherNotification> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.managerInquiry = inits.isInitialized("managerInquiry") ? new project.homelearn.entity.inquiry.QManagerInquiry(forProperty("managerInquiry"), inits.get("managerInquiry")) : null;
        this.questionBoard = inits.isInitialized("questionBoard") ? new project.homelearn.entity.board.QQuestionBoard(forProperty("questionBoard"), inits.get("questionBoard")) : null;
        this.questionBoardComment = inits.isInitialized("questionBoardComment") ? new project.homelearn.entity.board.comment.QQuestionBoardComment(forProperty("questionBoardComment"), inits.get("questionBoardComment")) : null;
        this.teacherInquiry = inits.isInitialized("teacherInquiry") ? new project.homelearn.entity.inquiry.QTeacherInquiry(forProperty("teacherInquiry"), inits.get("teacherInquiry")) : null;
        this.user = inits.isInitialized("user") ? new project.homelearn.entity.user.QUser(forProperty("user"), inits.get("user")) : null;
    }

}

