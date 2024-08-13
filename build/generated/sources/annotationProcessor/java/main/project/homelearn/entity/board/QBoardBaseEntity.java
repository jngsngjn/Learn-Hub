package project.homelearn.entity.board;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBoardBaseEntity is a Querydsl query type for BoardBaseEntity
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QBoardBaseEntity extends EntityPathBase<BoardBaseEntity> {

    private static final long serialVersionUID = -1474101339L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBoardBaseEntity boardBaseEntity = new QBoardBaseEntity("boardBaseEntity");

    public final project.homelearn.entity.QBaseEntity _super = new project.homelearn.entity.QBaseEntity(this);

    public final NumberPath<Integer> commentCount = createNumber("commentCount", Integer.class);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final StringPath imageName = createString("imageName");

    public final StringPath imagePath = createString("imagePath");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final StringPath title = createString("title");

    public final project.homelearn.entity.user.QUser user;

    public final NumberPath<Integer> viewCount = createNumber("viewCount", Integer.class);

    public QBoardBaseEntity(String variable) {
        this(BoardBaseEntity.class, forVariable(variable), INITS);
    }

    public QBoardBaseEntity(Path<? extends BoardBaseEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBoardBaseEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBoardBaseEntity(PathMetadata metadata, PathInits inits) {
        this(BoardBaseEntity.class, metadata, inits);
    }

    public QBoardBaseEntity(Class<? extends BoardBaseEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new project.homelearn.entity.user.QUser(forProperty("user"), inits.get("user")) : null;
    }

}

