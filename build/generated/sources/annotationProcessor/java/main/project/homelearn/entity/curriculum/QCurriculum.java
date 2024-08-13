package project.homelearn.entity.curriculum;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCurriculum is a Querydsl query type for Curriculum
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCurriculum extends EntityPathBase<Curriculum> {

    private static final long serialVersionUID = 1950680353L;

    public static final QCurriculum curriculum = new QCurriculum("curriculum");

    public final project.homelearn.entity.QBaseEntity _super = new project.homelearn.entity.QBaseEntity(this);

    public final StringPath color = createString("color");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final DatePath<java.time.LocalDate> endDate = createDate("endDate", java.time.LocalDate.class);

    public final ListPath<project.homelearn.entity.user.EnrollList, project.homelearn.entity.user.QEnrollList> enrollLists = this.<project.homelearn.entity.user.EnrollList, project.homelearn.entity.user.QEnrollList>createList("enrollLists", project.homelearn.entity.user.EnrollList.class, project.homelearn.entity.user.QEnrollList.class, PathInits.DIRECT2);

    public final StringPath fullName = createString("fullName");

    public final ListPath<project.homelearn.entity.homework.Homework, project.homelearn.entity.homework.QHomework> homeworks = this.<project.homelearn.entity.homework.Homework, project.homelearn.entity.homework.QHomework>createList("homeworks", project.homelearn.entity.homework.Homework.class, project.homelearn.entity.homework.QHomework.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<Lecture, QLecture> lectures = this.<Lecture, QLecture>createList("lectures", Lecture.class, QLecture.class, PathInits.DIRECT2);

    public final ListPath<project.homelearn.entity.calendar.ManagerCalendar, project.homelearn.entity.calendar.QManagerCalendar> managerCalendars = this.<project.homelearn.entity.calendar.ManagerCalendar, project.homelearn.entity.calendar.QManagerCalendar>createList("managerCalendars", project.homelearn.entity.calendar.ManagerCalendar.class, project.homelearn.entity.calendar.QManagerCalendar.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final StringPath name = createString("name");

    public final DatePath<java.time.LocalDate> startDate = createDate("startDate", java.time.LocalDate.class);

    public final ListPath<project.homelearn.entity.student.badge.StudentBadge, project.homelearn.entity.student.badge.QStudentBadge> studentBadges = this.<project.homelearn.entity.student.badge.StudentBadge, project.homelearn.entity.student.badge.QStudentBadge>createList("studentBadges", project.homelearn.entity.student.badge.StudentBadge.class, project.homelearn.entity.student.badge.QStudentBadge.class, PathInits.DIRECT2);

    public final ListPath<Subject, QSubject> subjects = this.<Subject, QSubject>createList("subjects", Subject.class, QSubject.class, PathInits.DIRECT2);

    public final ListPath<project.homelearn.entity.survey.Survey, project.homelearn.entity.survey.QSurvey> surveys = this.<project.homelearn.entity.survey.Survey, project.homelearn.entity.survey.QSurvey>createList("surveys", project.homelearn.entity.survey.Survey.class, project.homelearn.entity.survey.QSurvey.class, PathInits.DIRECT2);

    public final ListPath<project.homelearn.entity.teacher.TeacherBoard, project.homelearn.entity.teacher.QTeacherBoard> teacherBoards = this.<project.homelearn.entity.teacher.TeacherBoard, project.homelearn.entity.teacher.QTeacherBoard>createList("teacherBoards", project.homelearn.entity.teacher.TeacherBoard.class, project.homelearn.entity.teacher.QTeacherBoard.class, PathInits.DIRECT2);

    public final NumberPath<Long> th = createNumber("th", Long.class);

    public final EnumPath<CurriculumType> type = createEnum("type", CurriculumType.class);

    public final ListPath<project.homelearn.entity.user.User, project.homelearn.entity.user.QUser> users = this.<project.homelearn.entity.user.User, project.homelearn.entity.user.QUser>createList("users", project.homelearn.entity.user.User.class, project.homelearn.entity.user.QUser.class, PathInits.DIRECT2);

    public final ListPath<project.homelearn.entity.vote.Vote, project.homelearn.entity.vote.QVote> votes = this.<project.homelearn.entity.vote.Vote, project.homelearn.entity.vote.QVote>createList("votes", project.homelearn.entity.vote.Vote.class, project.homelearn.entity.vote.QVote.class, PathInits.DIRECT2);

    public QCurriculum(String variable) {
        super(Curriculum.class, forVariable(variable));
    }

    public QCurriculum(Path<? extends Curriculum> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCurriculum(PathMetadata metadata) {
        super(Curriculum.class, metadata);
    }

}

