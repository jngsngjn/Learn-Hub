package project.homelearn.entity.student.badge;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBadge is a Querydsl query type for Badge
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBadge extends EntityPathBase<Badge> {

    private static final long serialVersionUID = -1254783944L;

    public static final QBadge badge = new QBadge("badge");

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imageName = createString("imageName");

    public final StringPath imagePath = createString("imagePath");

    public final StringPath name = createString("name");

    public final ListPath<StudentBadge, QStudentBadge> studentBadges = this.<StudentBadge, QStudentBadge>createList("studentBadges", StudentBadge.class, QStudentBadge.class, PathInits.DIRECT2);

    public final ListPath<project.homelearn.entity.notification.student.StudentNotification, project.homelearn.entity.notification.student.QStudentNotification> studentNotifications = this.<project.homelearn.entity.notification.student.StudentNotification, project.homelearn.entity.notification.student.QStudentNotification>createList("studentNotifications", project.homelearn.entity.notification.student.StudentNotification.class, project.homelearn.entity.notification.student.QStudentNotification.class, PathInits.DIRECT2);

    public QBadge(String variable) {
        super(Badge.class, forVariable(variable));
    }

    public QBadge(Path<? extends Badge> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBadge(PathMetadata metadata) {
        super(Badge.class, metadata);
    }

}

