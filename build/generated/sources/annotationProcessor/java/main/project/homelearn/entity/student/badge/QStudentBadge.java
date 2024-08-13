package project.homelearn.entity.student.badge;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStudentBadge is a Querydsl query type for StudentBadge
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStudentBadge extends EntityPathBase<StudentBadge> {

    private static final long serialVersionUID = 1511492595L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStudentBadge studentBadge = new QStudentBadge("studentBadge");

    public final QBadge badge;

    public final project.homelearn.entity.curriculum.QCurriculum curriculum;

    public final DatePath<java.time.LocalDate> getDate = createDate("getDate", java.time.LocalDate.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final project.homelearn.entity.user.QUser user;

    public QStudentBadge(String variable) {
        this(StudentBadge.class, forVariable(variable), INITS);
    }

    public QStudentBadge(Path<? extends StudentBadge> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStudentBadge(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStudentBadge(PathMetadata metadata, PathInits inits) {
        this(StudentBadge.class, metadata, inits);
    }

    public QStudentBadge(Class<? extends StudentBadge> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.badge = inits.isInitialized("badge") ? new QBadge(forProperty("badge")) : null;
        this.curriculum = inits.isInitialized("curriculum") ? new project.homelearn.entity.curriculum.QCurriculum(forProperty("curriculum")) : null;
        this.user = inits.isInitialized("user") ? new project.homelearn.entity.user.QUser(forProperty("user"), inits.get("user")) : null;
    }

}

