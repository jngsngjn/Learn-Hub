package project.homelearn.entity.vote;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStudentVote is a Querydsl query type for StudentVote
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStudentVote extends EntityPathBase<StudentVote> {

    private static final long serialVersionUID = -1916848754L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStudentVote studentVote = new QStudentVote("studentVote");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final project.homelearn.entity.user.QUser user;

    public final QVote vote;

    public final QVoteContent voteContent;

    public QStudentVote(String variable) {
        this(StudentVote.class, forVariable(variable), INITS);
    }

    public QStudentVote(Path<? extends StudentVote> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStudentVote(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStudentVote(PathMetadata metadata, PathInits inits) {
        this(StudentVote.class, metadata, inits);
    }

    public QStudentVote(Class<? extends StudentVote> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new project.homelearn.entity.user.QUser(forProperty("user"), inits.get("user")) : null;
        this.vote = inits.isInitialized("vote") ? new QVote(forProperty("vote"), inits.get("vote")) : null;
        this.voteContent = inits.isInitialized("voteContent") ? new QVoteContent(forProperty("voteContent"), inits.get("voteContent")) : null;
    }

}

