package project.homelearn.entity.teacher;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTeacher is a Querydsl query type for Teacher
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTeacher extends EntityPathBase<Teacher> {

    private static final long serialVersionUID = -1765847767L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTeacher teacher = new QTeacher("teacher");

    public final project.homelearn.entity.user.QUser _super;

    //inherited
    public final ListPath<project.homelearn.entity.student.Attendance, project.homelearn.entity.student.QAttendance> attendances;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate;

    // inherited
    public final project.homelearn.entity.curriculum.QCurriculum curriculum;

    //inherited
    public final StringPath email;

    //inherited
    public final ListPath<project.homelearn.entity.board.comment.FreeBoardComment, project.homelearn.entity.board.comment.QFreeBoardComment> freeBoardComments;

    //inherited
    public final ListPath<project.homelearn.entity.board.FreeBoard, project.homelearn.entity.board.QFreeBoard> freeBoards;

    //inherited
    public final EnumPath<project.homelearn.entity.user.Gender> gender;

    //inherited
    public final NumberPath<Long> id;

    //inherited
    public final StringPath imageName;

    //inherited
    public final StringPath imagePath;

    //inherited
    public final NumberPath<Integer> lectureCount;

    //inherited
    public final ListPath<project.homelearn.entity.user.LoginHistory, project.homelearn.entity.user.QLoginHistory> loginHistories;

    //inherited
    public final ListPath<project.homelearn.entity.inquiry.ManagerInquiry, project.homelearn.entity.inquiry.QManagerInquiry> managerInquiries;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate;

    //inherited
    public final StringPath name;

    //inherited
    public final StringPath password;

    //inherited
    public final NumberPath<Integer> passwordChangeCount;

    //inherited
    public final StringPath phone;

    //inherited
    public final ListPath<project.homelearn.entity.board.comment.QuestionBoardComment, project.homelearn.entity.board.comment.QQuestionBoardComment> questionBoardComments;

    //inherited
    public final ListPath<project.homelearn.entity.board.QuestionBoard, project.homelearn.entity.board.QQuestionBoard> questionBoards;

    //inherited
    public final ListPath<project.homelearn.entity.board.scrap.QuestionScrap, project.homelearn.entity.board.scrap.QQuestionScrap> questionScraps;

    //inherited
    public final EnumPath<project.homelearn.entity.user.Role> role;

    //inherited
    public final ListPath<project.homelearn.entity.homework.StudentHomework, project.homelearn.entity.homework.QStudentHomework> studentHomeworks;

    //inherited
    public final ListPath<project.homelearn.entity.curriculum.StudentLecture, project.homelearn.entity.curriculum.QStudentLecture> studentLectures;

    //inherited
    public final ListPath<project.homelearn.entity.notification.student.StudentNotification, project.homelearn.entity.notification.student.QStudentNotification> studentNotifications;

    //inherited
    public final ListPath<project.homelearn.entity.vote.StudentVote, project.homelearn.entity.vote.QStudentVote> studentVotes;

    //inherited
    public final ListPath<project.homelearn.entity.survey.SurveyAnswer, project.homelearn.entity.survey.QSurveyAnswer> surveyAnswers;

    //inherited
    public final BooleanPath surveyCompleted;

    //inherited
    public final ListPath<project.homelearn.entity.calendar.TeacherCalendar, project.homelearn.entity.calendar.QTeacherCalendar> teacherCalendars;

    //inherited
    public final ListPath<project.homelearn.entity.inquiry.TeacherInquiry, project.homelearn.entity.inquiry.QTeacherInquiry> teacherInquiries;

    //inherited
    public final ListPath<project.homelearn.entity.notification.teacher.TeacherNotification, project.homelearn.entity.notification.teacher.QTeacherNotification> teacherNotifications;

    //inherited
    public final StringPath username;

    public QTeacher(String variable) {
        this(Teacher.class, forVariable(variable), INITS);
    }

    public QTeacher(Path<? extends Teacher> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTeacher(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTeacher(PathMetadata metadata, PathInits inits) {
        this(Teacher.class, metadata, inits);
    }

    public QTeacher(Class<? extends Teacher> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new project.homelearn.entity.user.QUser(type, metadata, inits);
        this.attendances = _super.attendances;
        this.createdDate = _super.createdDate;
        this.curriculum = _super.curriculum;
        this.email = _super.email;
        this.freeBoardComments = _super.freeBoardComments;
        this.freeBoards = _super.freeBoards;
        this.gender = _super.gender;
        this.id = _super.id;
        this.imageName = _super.imageName;
        this.imagePath = _super.imagePath;
        this.lectureCount = _super.lectureCount;
        this.loginHistories = _super.loginHistories;
        this.managerInquiries = _super.managerInquiries;
        this.modifiedDate = _super.modifiedDate;
        this.name = _super.name;
        this.password = _super.password;
        this.passwordChangeCount = _super.passwordChangeCount;
        this.phone = _super.phone;
        this.questionBoardComments = _super.questionBoardComments;
        this.questionBoards = _super.questionBoards;
        this.questionScraps = _super.questionScraps;
        this.role = _super.role;
        this.studentHomeworks = _super.studentHomeworks;
        this.studentLectures = _super.studentLectures;
        this.studentNotifications = _super.studentNotifications;
        this.studentVotes = _super.studentVotes;
        this.surveyAnswers = _super.surveyAnswers;
        this.surveyCompleted = _super.surveyCompleted;
        this.teacherCalendars = _super.teacherCalendars;
        this.teacherInquiries = _super.teacherInquiries;
        this.teacherNotifications = _super.teacherNotifications;
        this.username = _super.username;
    }

}

