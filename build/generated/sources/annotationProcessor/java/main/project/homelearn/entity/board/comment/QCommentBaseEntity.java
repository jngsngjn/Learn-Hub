package project.homelearn.entity.board.comment;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCommentBaseEntity is a Querydsl query type for CommentBaseEntity
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QCommentBaseEntity extends EntityPathBase<CommentBaseEntity> {

    private static final long serialVersionUID = 1432821839L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCommentBaseEntity commentBaseEntity = new QCommentBaseEntity("commentBaseEntity");

    public final project.homelearn.entity.QBaseEntity _super = new project.homelearn.entity.QBaseEntity(this);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final project.homelearn.entity.user.QUser user;

    public QCommentBaseEntity(String variable) {
        this(CommentBaseEntity.class, forVariable(variable), INITS);
    }

    public QCommentBaseEntity(Path<? extends CommentBaseEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCommentBaseEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCommentBaseEntity(PathMetadata metadata, PathInits inits) {
        this(CommentBaseEntity.class, metadata, inits);
    }

    public QCommentBaseEntity(Class<? extends CommentBaseEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new project.homelearn.entity.user.QUser(forProperty("user"), inits.get("user")) : null;
    }

}

