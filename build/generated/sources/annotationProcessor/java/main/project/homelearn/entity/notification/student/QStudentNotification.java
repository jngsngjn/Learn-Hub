package project.homelearn.entity.notification.student;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStudentNotification is a Querydsl query type for StudentNotification
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStudentNotification extends EntityPathBase<StudentNotification> {

    private static final long serialVersionUID = 87820253L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStudentNotification studentNotification = new QStudentNotification("studentNotification");

    public final project.homelearn.entity.student.badge.QBadge badge;

    public final project.homelearn.entity.board.QFreeBoard freeBoard;

    public final project.homelearn.entity.board.comment.QFreeBoardComment freeBoardComment;

    public final project.homelearn.entity.homework.QHomework homework;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final project.homelearn.entity.inquiry.QManagerInquiry managerInquiry;

    public final project.homelearn.entity.board.QQuestionBoard questionBoard;

    public final project.homelearn.entity.board.comment.QQuestionBoardComment questionBoardComment;

    public final project.homelearn.entity.survey.QSurvey survey;

    public final project.homelearn.entity.inquiry.QTeacherInquiry teacherInquiry;

    public final EnumPath<StudentNotificationType> type = createEnum("type", StudentNotificationType.class);

    public final project.homelearn.entity.user.QUser user;

    public QStudentNotification(String variable) {
        this(StudentNotification.class, forVariable(variable), INITS);
    }

    public QStudentNotification(Path<? extends StudentNotification> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStudentNotification(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStudentNotification(PathMetadata metadata, PathInits inits) {
        this(StudentNotification.class, metadata, inits);
    }

    public QStudentNotification(Class<? extends StudentNotification> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.badge = inits.isInitialized("badge") ? new project.homelearn.entity.student.badge.QBadge(forProperty("badge")) : null;
        this.freeBoard = inits.isInitialized("freeBoard") ? new project.homelearn.entity.board.QFreeBoard(forProperty("freeBoard"), inits.get("freeBoard")) : null;
        this.freeBoardComment = inits.isInitialized("freeBoardComment") ? new project.homelearn.entity.board.comment.QFreeBoardComment(forProperty("freeBoardComment"), inits.get("freeBoardComment")) : null;
        this.homework = inits.isInitialized("homework") ? new project.homelearn.entity.homework.QHomework(forProperty("homework"), inits.get("homework")) : null;
        this.managerInquiry = inits.isInitialized("managerInquiry") ? new project.homelearn.entity.inquiry.QManagerInquiry(forProperty("managerInquiry"), inits.get("managerInquiry")) : null;
        this.questionBoard = inits.isInitialized("questionBoard") ? new project.homelearn.entity.board.QQuestionBoard(forProperty("questionBoard"), inits.get("questionBoard")) : null;
        this.questionBoardComment = inits.isInitialized("questionBoardComment") ? new project.homelearn.entity.board.comment.QQuestionBoardComment(forProperty("questionBoardComment"), inits.get("questionBoardComment")) : null;
        this.survey = inits.isInitialized("survey") ? new project.homelearn.entity.survey.QSurvey(forProperty("survey"), inits.get("survey")) : null;
        this.teacherInquiry = inits.isInitialized("teacherInquiry") ? new project.homelearn.entity.inquiry.QTeacherInquiry(forProperty("teacherInquiry"), inits.get("teacherInquiry")) : null;
        this.user = inits.isInitialized("user") ? new project.homelearn.entity.user.QUser(forProperty("user"), inits.get("user")) : null;
    }

}

