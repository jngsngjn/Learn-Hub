package project.homelearn.entity.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLoginHistory is a Querydsl query type for LoginHistory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLoginHistory extends EntityPathBase<LoginHistory> {

    private static final long serialVersionUID = -1161860863L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLoginHistory loginHistory = new QLoginHistory("loginHistory");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.time.LocalDateTime> loginDateTime = createDateTime("loginDateTime", java.time.LocalDateTime.class);

    public final QUser user;

    public QLoginHistory(String variable) {
        this(LoginHistory.class, forVariable(variable), INITS);
    }

    public QLoginHistory(Path<? extends LoginHistory> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QLoginHistory(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QLoginHistory(PathMetadata metadata, PathInits inits) {
        this(LoginHistory.class, metadata, inits);
    }

    public QLoginHistory(Class<? extends LoginHistory> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user"), inits.get("user")) : null;
    }

}

