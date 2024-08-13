package project.homelearn.entity.survey;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSurvey is a Querydsl query type for Survey
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSurvey extends EntityPathBase<Survey> {

    private static final long serialVersionUID = -618346303L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSurvey survey = new QSurvey("survey");

    public final project.homelearn.entity.curriculum.QCurriculum curriculum;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isFinished = createBoolean("isFinished");

    public final NumberPath<Integer> round = createNumber("round", Integer.class);

    public final ListPath<project.homelearn.entity.notification.student.StudentNotification, project.homelearn.entity.notification.student.QStudentNotification> studentNotifications = this.<project.homelearn.entity.notification.student.StudentNotification, project.homelearn.entity.notification.student.QStudentNotification>createList("studentNotifications", project.homelearn.entity.notification.student.StudentNotification.class, project.homelearn.entity.notification.student.QStudentNotification.class, PathInits.DIRECT2);

    public final ListPath<SurveyAnswer, QSurveyAnswer> surveyAnswers = this.<SurveyAnswer, QSurveyAnswer>createList("surveyAnswers", SurveyAnswer.class, QSurveyAnswer.class, PathInits.DIRECT2);

    public final StringPath title = createString("title");

    public QSurvey(String variable) {
        this(Survey.class, forVariable(variable), INITS);
    }

    public QSurvey(Path<? extends Survey> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSurvey(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSurvey(PathMetadata metadata, PathInits inits) {
        this(Survey.class, metadata, inits);
    }

    public QSurvey(Class<? extends Survey> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.curriculum = inits.isInitialized("curriculum") ? new project.homelearn.entity.curriculum.QCurriculum(forProperty("curriculum")) : null;
    }

}

