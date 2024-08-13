package project.homelearn.entity.inquiry;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QManagerInquiry is a Querydsl query type for ManagerInquiry
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QManagerInquiry extends EntityPathBase<ManagerInquiry> {

    private static final long serialVersionUID = 1517973934L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QManagerInquiry managerInquiry = new QManagerInquiry("managerInquiry");

    public final QInquiryBaseEntity _super;

    //inherited
    public final StringPath content;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<project.homelearn.entity.notification.manager.ManagerNotification, project.homelearn.entity.notification.manager.QManagerNotification> managerNotifications = this.<project.homelearn.entity.notification.manager.ManagerNotification, project.homelearn.entity.notification.manager.QManagerNotification>createList("managerNotifications", project.homelearn.entity.notification.manager.ManagerNotification.class, project.homelearn.entity.notification.manager.QManagerNotification.class, PathInits.DIRECT2);

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

    public QManagerInquiry(String variable) {
        this(ManagerInquiry.class, forVariable(variable), INITS);
    }

    public QManagerInquiry(Path<? extends ManagerInquiry> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QManagerInquiry(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QManagerInquiry(PathMetadata metadata, PathInits inits) {
        this(ManagerInquiry.class, metadata, inits);
    }

    public QManagerInquiry(Class<? extends ManagerInquiry> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new QInquiryBaseEntity(type, metadata, inits);
        this.content = _super.content;
        this.createdDate = _super.createdDate;
        this.modifiedDate = _super.modifiedDate;
        this.response = _super.response;
        this.responseDate = _super.responseDate;
        this.title = _super.title;
        this.user = _super.user;
    }

}

