package project.homelearn.entity.manager;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QManagerBoard is a Querydsl query type for ManagerBoard
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QManagerBoard extends EntityPathBase<ManagerBoard> {

    private static final long serialVersionUID = -1310432153L;

    public static final QManagerBoard managerBoard = new QManagerBoard("managerBoard");

    public final project.homelearn.entity.QBaseEntity _super = new project.homelearn.entity.QBaseEntity(this);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final BooleanPath emergency = createBoolean("emergency");

    public final StringPath filePath = createString("filePath");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final StringPath storeFileName = createString("storeFileName");

    public final StringPath title = createString("title");

    public final StringPath uploadFileName = createString("uploadFileName");

    public QManagerBoard(String variable) {
        super(ManagerBoard.class, forVariable(variable));
    }

    public QManagerBoard(Path<? extends ManagerBoard> path) {
        super(path.getType(), path.getMetadata());
    }

    public QManagerBoard(PathMetadata metadata) {
        super(ManagerBoard.class, metadata);
    }

}

