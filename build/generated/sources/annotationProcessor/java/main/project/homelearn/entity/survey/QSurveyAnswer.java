package project.homelearn.entity.survey;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSurveyAnswer is a Querydsl query type for SurveyAnswer
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSurveyAnswer extends EntityPathBase<SurveyAnswer> {

    private static final long serialVersionUID = 623943647L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSurveyAnswer surveyAnswer = new QSurveyAnswer("surveyAnswer");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> rating = createNumber("rating", Integer.class);

    public final QSurvey survey;

    public final QSurveyContent surveyContent;

    public final StringPath textAnswer = createString("textAnswer");

    public final project.homelearn.entity.user.QUser user;

    public QSurveyAnswer(String variable) {
        this(SurveyAnswer.class, forVariable(variable), INITS);
    }

    public QSurveyAnswer(Path<? extends SurveyAnswer> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSurveyAnswer(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSurveyAnswer(PathMetadata metadata, PathInits inits) {
        this(SurveyAnswer.class, metadata, inits);
    }

    public QSurveyAnswer(Class<? extends SurveyAnswer> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.survey = inits.isInitialized("survey") ? new QSurvey(forProperty("survey"), inits.get("survey")) : null;
        this.surveyContent = inits.isInitialized("surveyContent") ? new QSurveyContent(forProperty("surveyContent")) : null;
        this.user = inits.isInitialized("user") ? new project.homelearn.entity.user.QUser(forProperty("user"), inits.get("user")) : null;
    }

}

