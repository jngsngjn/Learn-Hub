package project.homelearn.entity.inquiry;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTeacherInquiry is a Querydsl query type for TeacherInquiry
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTeacherInquiry extends EntityPathBase<TeacherInquiry> {

    private static final long serialVersionUID = -73105447L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTeacherInquiry teacherInquiry = new QTeacherInquiry("teacherInquiry");

    public final QInquiryBaseEntity _super;

    //inherited
    public final StringPath content;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate;

    public final project.homelearn.entity.curriculum.QCurriculum curriculum;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate;

    //inherited
    public final StringPath response;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> responseDate;

    public final ListPath<project.homelearn.entity.notification.student.StudentNotification, project.homelearn.entity.notification.student.QStudentNotification> studentNotifications = this.<project.homelearn.entity.notification.student.StudentNotification, project.homelearn.entity.notification.student.QStudentNotification>createList("studentNotifications", project.homelearn.entity.notification.student.StudentNotification.class, project.homelearn.entity.notification.student.QStudentNotification.class, PathInits.DIRECT2);

    public final ListPath<project.homelearn.entity.notification.teacher.TeacherNotification, project.homelearn.entity.notification.teacher.QTeacherNotification> teacherNotifications = this.<project.homelearn.entity.notification.teacher.TeacherNotification, project.homelearn.entity.notification.teacher.QTeacherNotification>createList("teacherNotifications", project.homelearn.entity.notification.teacher.TeacherNotification.class, project.homelearn.entity.notification.teacher.QTeacherNotification.class, PathInits.DIRECT2);

    //inherited
    public final StringPath title;

    // inherited
    public final project.homelearn.entity.user.QUser user;

    public QTeacherInquiry(String variable) {
        this(TeacherInquiry.class, forVariable(variable), INITS);
    }

    public QTeacherInquiry(Path<? extends TeacherInquiry> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTeacherInquiry(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTeacherInquiry(PathMetadata metadata, PathInits inits) {
        this(TeacherInquiry.class, metadata, inits);
    }

    public QTeacherInquiry(Class<? extends TeacherInquiry> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new QInquiryBaseEntity(type, metadata, inits);
        this.content = _super.content;
        this.createdDate = _super.createdDate;
        this.curriculum = inits.isInitialized("curriculum") ? new project.homelearn.entity.curriculum.QCurriculum(forProperty("curriculum")) : null;
        this.modifiedDate = _super.modifiedDate;
        this.response = _super.response;
        this.responseDate = _super.responseDate;
        this.title = _super.title;
        this.user = _super.user;
    }

}

