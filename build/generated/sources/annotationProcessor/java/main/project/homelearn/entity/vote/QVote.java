package project.homelearn.entity.vote;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QVote is a Querydsl query type for Vote
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QVote extends EntityPathBase<Vote> {

    private static final long serialVersionUID = 1687834945L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QVote vote = new QVote("vote");

    public final project.homelearn.entity.QBaseEntity _super = new project.homelearn.entity.QBaseEntity(this);

    public final ListPath<VoteContent, QVoteContent> contents = this.<VoteContent, QVoteContent>createList("contents", VoteContent.class, QVoteContent.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final project.homelearn.entity.curriculum.QCurriculum curriculum;

    public final StringPath description = createString("description");

    public final DateTimePath<java.time.LocalDateTime> endTime = createDateTime("endTime", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isAnonymous = createBoolean("isAnonymous");

    public final BooleanPath isMultipleChoice = createBoolean("isMultipleChoice");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final DateTimePath<java.time.LocalDateTime> startTime = createDateTime("startTime", java.time.LocalDateTime.class);

    public final ListPath<StudentVote, QStudentVote> studentVotes = this.<StudentVote, QStudentVote>createList("studentVotes", StudentVote.class, QStudentVote.class, PathInits.DIRECT2);

    public final StringPath title = createString("title");

    public QVote(String variable) {
        this(Vote.class, forVariable(variable), INITS);
    }

    public QVote(Path<? extends Vote> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QVote(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QVote(PathMetadata metadata, PathInits inits) {
        this(Vote.class, metadata, inits);
    }

    public QVote(Class<? extends Vote> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.curriculum = inits.isInitialized("curriculum") ? new project.homelearn.entity.curriculum.QCurriculum(forProperty("curriculum")) : null;
    }

}

