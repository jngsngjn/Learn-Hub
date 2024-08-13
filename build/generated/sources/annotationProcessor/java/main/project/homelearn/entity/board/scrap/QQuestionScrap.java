package project.homelearn.entity.board.scrap;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QQuestionScrap is a Querydsl query type for QuestionScrap
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QQuestionScrap extends EntityPathBase<QuestionScrap> {

    private static final long serialVersionUID = 847670457L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QQuestionScrap questionScrap = new QQuestionScrap("questionScrap");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final project.homelearn.entity.board.QQuestionBoard questionBoard;

    public final project.homelearn.entity.user.QUser user;

    public QQuestionScrap(String variable) {
        this(QuestionScrap.class, forVariable(variable), INITS);
    }

    public QQuestionScrap(Path<? extends QuestionScrap> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QQuestionScrap(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QQuestionScrap(PathMetadata metadata, PathInits inits) {
        this(QuestionScrap.class, metadata, inits);
    }

    public QQuestionScrap(Class<? extends QuestionScrap> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.questionBoard = inits.isInitialized("questionBoard") ? new project.homelearn.entity.board.QQuestionBoard(forProperty("questionBoard"), inits.get("questionBoard")) : null;
        this.user = inits.isInitialized("user") ? new project.homelearn.entity.user.QUser(forProperty("user"), inits.get("user")) : null;
    }

}

