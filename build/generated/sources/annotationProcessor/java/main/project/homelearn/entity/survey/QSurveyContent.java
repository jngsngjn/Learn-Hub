package project.homelearn.entity.survey;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSurveyContent is a Querydsl query type for SurveyContent
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSurveyContent extends EntityPathBase<SurveyContent> {

    private static final long serialVersionUID = -333653896L;

    public static final QSurveyContent surveyContent = new QSurveyContent("surveyContent");

    public final StringPath content = createString("content");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final EnumPath<QuestionType> questionType = createEnum("questionType", QuestionType.class);

    public final ListPath<SurveyAnswer, QSurveyAnswer> surveyAnswers = this.<SurveyAnswer, QSurveyAnswer>createList("surveyAnswers", SurveyAnswer.class, QSurveyAnswer.class, PathInits.DIRECT2);

    public QSurveyContent(String variable) {
        super(SurveyContent.class, forVariable(variable));
    }

    public QSurveyContent(Path<? extends SurveyContent> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSurveyContent(PathMetadata metadata) {
        super(SurveyContent.class, metadata);
    }

}

