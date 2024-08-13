package project.homelearn.entity.inquiry;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QInquiryBaseEntity is a Querydsl query type for InquiryBaseEntity
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QInquiryBaseEntity extends EntityPathBase<InquiryBaseEntity> {

    private static final long serialVersionUID = 1428263527L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QInquiryBaseEntity inquiryBaseEntity = new QInquiryBaseEntity("inquiryBaseEntity");

    public final project.homelearn.entity.QBaseEntity _super = new project.homelearn.entity.QBaseEntity(this);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final StringPath response = createString("response");

    public final DateTimePath<java.time.LocalDateTime> responseDate = createDateTime("responseDate", java.time.LocalDateTime.class);

    public final StringPath title = createString("title");

    public final project.homelearn.entity.user.QUser user;

    public QInquiryBaseEntity(String variable) {
        this(InquiryBaseEntity.class, forVariable(variable), INITS);
    }

    public QInquiryBaseEntity(Path<? extends InquiryBaseEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QInquiryBaseEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QInquiryBaseEntity(PathMetadata metadata, PathInits inits) {
        this(InquiryBaseEntity.class, metadata, inits);
    }

    public QInquiryBaseEntity(Class<? extends InquiryBaseEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new project.homelearn.entity.user.QUser(forProperty("user"), inits.get("user")) : null;
    }

}

