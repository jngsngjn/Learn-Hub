package project.homelearn.entity.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = 1821095393L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUser user = new QUser("user");

    public final project.homelearn.entity.QBaseEntity _super = new project.homelearn.entity.QBaseEntity(this);

    public final ListPath<project.homelearn.entity.student.Attendance, project.homelearn.entity.student.QAttendance> attendances = this.<project.homelearn.entity.student.Attendance, project.homelearn.entity.student.QAttendance>createList("attendances", project.homelearn.entity.student.Attendance.class, project.homelearn.entity.student.QAttendance.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final project.homelearn.entity.curriculum.QCurriculum curriculum;

    public final StringPath email = createString("email");

    public final ListPath<project.homelearn.entity.board.comment.FreeBoardComment, project.homelearn.entity.board.comment.QFreeBoardComment> freeBoardComments = this.<project.homelearn.entity.board.comment.FreeBoardComment, project.homelearn.entity.board.comment.QFreeBoardComment>createList("freeBoardComments", project.homelearn.entity.board.comment.FreeBoardComment.class, project.homelearn.entity.board.comment.QFreeBoardComment.class, PathInits.DIRECT2);

    public final ListPath<project.homelearn.entity.board.FreeBoard, project.homelearn.entity.board.QFreeBoard> freeBoards = this.<project.homelearn.entity.board.FreeBoard, project.homelearn.entity.board.QFreeBoard>createList("freeBoards", project.homelearn.entity.board.FreeBoard.class, project.homelearn.entity.board.QFreeBoard.class, PathInits.DIRECT2);

    public final EnumPath<Gender> gender = createEnum("gender", Gender.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imageName = createString("imageName");

    public final StringPath imagePath = createString("imagePath");

    public final NumberPath<Integer> lectureCount = createNumber("lectureCount", Integer.class);

    public final ListPath<LoginHistory, QLoginHistory> loginHistories = this.<LoginHistory, QLoginHistory>createList("loginHistories", LoginHistory.class, QLoginHistory.class, PathInits.DIRECT2);

    public final ListPath<project.homelearn.entity.inquiry.ManagerInquiry, project.homelearn.entity.inquiry.QManagerInquiry> managerInquiries = this.<project.homelearn.entity.inquiry.ManagerInquiry, project.homelearn.entity.inquiry.QManagerInquiry>createList("managerInquiries", project.homelearn.entity.inquiry.ManagerInquiry.class, project.homelearn.entity.inquiry.QManagerInquiry.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final StringPath name = createString("name");

    public final StringPath password = createString("password");

    public final NumberPath<Integer> passwordChangeCount = createNumber("passwordChangeCount", Integer.class);

    public final StringPath phone = createString("phone");

    public final ListPath<project.homelearn.entity.board.comment.QuestionBoardComment, project.homelearn.entity.board.comment.QQuestionBoardComment> questionBoardComments = this.<project.homelearn.entity.board.comment.QuestionBoardComment, project.homelearn.entity.board.comment.QQuestionBoardComment>createList("questionBoardComments", project.homelearn.entity.board.comment.QuestionBoardComment.class, project.homelearn.entity.board.comment.QQuestionBoardComment.class, PathInits.DIRECT2);

    public final ListPath<project.homelearn.entity.board.QuestionBoard, project.homelearn.entity.board.QQuestionBoard> questionBoards = this.<project.homelearn.entity.board.QuestionBoard, project.homelearn.entity.board.QQuestionBoard>createList("questionBoards", project.homelearn.entity.board.QuestionBoard.class, project.homelearn.entity.board.QQuestionBoard.class, PathInits.DIRECT2);

    public final ListPath<project.homelearn.entity.board.scrap.QuestionScrap, project.homelearn.entity.board.scrap.QQuestionScrap> questionScraps = this.<project.homelearn.entity.board.scrap.QuestionScrap, project.homelearn.entity.board.scrap.QQuestionScrap>createList("questionScraps", project.homelearn.entity.board.scrap.QuestionScrap.class, project.homelearn.entity.board.scrap.QQuestionScrap.class, PathInits.DIRECT2);

    public final EnumPath<Role> role = createEnum("role", Role.class);

    public final ListPath<project.homelearn.entity.homework.StudentHomework, project.homelearn.entity.homework.QStudentHomework> studentHomeworks = this.<project.homelearn.entity.homework.StudentHomework, project.homelearn.entity.homework.QStudentHomework>createList("studentHomeworks", project.homelearn.entity.homework.StudentHomework.class, project.homelearn.entity.homework.QStudentHomework.class, PathInits.DIRECT2);

    public final ListPath<project.homelearn.entity.curriculum.StudentLecture, project.homelearn.entity.curriculum.QStudentLecture> studentLectures = this.<project.homelearn.entity.curriculum.StudentLecture, project.homelearn.entity.curriculum.QStudentLecture>createList("studentLectures", project.homelearn.entity.curriculum.StudentLecture.class, project.homelearn.entity.curriculum.QStudentLecture.class, PathInits.DIRECT2);

    public final ListPath<project.homelearn.entity.notification.student.StudentNotification, project.homelearn.entity.notification.student.QStudentNotification> studentNotifications = this.<project.homelearn.entity.notification.student.StudentNotification, project.homelearn.entity.notification.student.QStudentNotification>createList("studentNotifications", project.homelearn.entity.notification.student.StudentNotification.class, project.homelearn.entity.notification.student.QStudentNotification.class, PathInits.DIRECT2);

    public final ListPath<project.homelearn.entity.vote.StudentVote, project.homelearn.entity.vote.QStudentVote> studentVotes = this.<project.homelearn.entity.vote.StudentVote, project.homelearn.entity.vote.QStudentVote>createList("studentVotes", project.homelearn.entity.vote.StudentVote.class, project.homelearn.entity.vote.QStudentVote.class, PathInits.DIRECT2);

    public final ListPath<project.homelearn.entity.survey.SurveyAnswer, project.homelearn.entity.survey.QSurveyAnswer> surveyAnswers = this.<project.homelearn.entity.survey.SurveyAnswer, project.homelearn.entity.survey.QSurveyAnswer>createList("surveyAnswers", project.homelearn.entity.survey.SurveyAnswer.class, project.homelearn.entity.survey.QSurveyAnswer.class, PathInits.DIRECT2);

    public final BooleanPath surveyCompleted = createBoolean("surveyCompleted");

    public final ListPath<project.homelearn.entity.calendar.TeacherCalendar, project.homelearn.entity.calendar.QTeacherCalendar> teacherCalendars = this.<project.homelearn.entity.calendar.TeacherCalendar, project.homelearn.entity.calendar.QTeacherCalendar>createList("teacherCalendars", project.homelearn.entity.calendar.TeacherCalendar.class, project.homelearn.entity.calendar.QTeacherCalendar.class, PathInits.DIRECT2);

    public final ListPath<project.homelearn.entity.inquiry.TeacherInquiry, project.homelearn.entity.inquiry.QTeacherInquiry> teacherInquiries = this.<project.homelearn.entity.inquiry.TeacherInquiry, project.homelearn.entity.inquiry.QTeacherInquiry>createList("teacherInquiries", project.homelearn.entity.inquiry.TeacherInquiry.class, project.homelearn.entity.inquiry.QTeacherInquiry.class, PathInits.DIRECT2);

    public final ListPath<project.homelearn.entity.notification.teacher.TeacherNotification, project.homelearn.entity.notification.teacher.QTeacherNotification> teacherNotifications = this.<project.homelearn.entity.notification.teacher.TeacherNotification, project.homelearn.entity.notification.teacher.QTeacherNotification>createList("teacherNotifications", project.homelearn.entity.notification.teacher.TeacherNotification.class, project.homelearn.entity.notification.teacher.QTeacherNotification.class, PathInits.DIRECT2);

    public final StringPath username = createString("username");

    public QUser(String variable) {
        this(User.class, forVariable(variable), INITS);
    }

    public QUser(Path<? extends User> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUser(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUser(PathMetadata metadata, PathInits inits) {
        this(User.class, metadata, inits);
    }

    public QUser(Class<? extends User> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.curriculum = inits.isInitialized("curriculum") ? new project.homelearn.entity.curriculum.QCurriculum(forProperty("curriculum")) : null;
    }

}

