package project.homelearn.entity.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QEnrollList is a Querydsl query type for EnrollList
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QEnrollList extends EntityPathBase<EnrollList> {

    private static final long serialVersionUID = 448451578L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QEnrollList enrollList = new QEnrollList("enrollList");

    public final StringPath code = createString("code");

    public final project.homelearn.entity.curriculum.QCurriculum curriculum;

    public final StringPath email = createString("email");

    public final EnumPath<Gender> gender = createEnum("gender", Gender.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final StringPath phone = createString("phone");

    public QEnrollList(String variable) {
        this(EnrollList.class, forVariable(variable), INITS);
    }

    public QEnrollList(Path<? extends EnrollList> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QEnrollList(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QEnrollList(PathMetadata metadata, PathInits inits) {
        this(EnrollList.class, metadata, inits);
    }

    public QEnrollList(Class<? extends EnrollList> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.curriculum = inits.isInitialized("curriculum") ? new project.homelearn.entity.curriculum.QCurriculum(forProperty("curriculum")) : null;
    }

}

