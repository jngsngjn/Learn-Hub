package project.homelearn.entity.notification.manager;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QManagerNotification is a Querydsl query type for ManagerNotification
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QManagerNotification extends EntityPathBase<ManagerNotification> {

    private static final long serialVersionUID = 1561549505L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QManagerNotification managerNotification = new QManagerNotification("managerNotification");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final project.homelearn.entity.inquiry.QManagerInquiry managerInquiry;

    public final EnumPath<ManagerNotificationType> type = createEnum("type", ManagerNotificationType.class);

    public QManagerNotification(String variable) {
        this(ManagerNotification.class, forVariable(variable), INITS);
    }

    public QManagerNotification(Path<? extends ManagerNotification> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QManagerNotification(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QManagerNotification(PathMetadata metadata, PathInits inits) {
        this(ManagerNotification.class, metadata, inits);
    }

    public QManagerNotification(Class<? extends ManagerNotification> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.managerInquiry = inits.isInitialized("managerInquiry") ? new project.homelearn.entity.inquiry.QManagerInquiry(forProperty("managerInquiry"), inits.get("managerInquiry")) : null;
    }

}

