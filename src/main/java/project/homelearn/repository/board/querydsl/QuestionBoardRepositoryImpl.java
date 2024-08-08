package project.homelearn.repository.board.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import project.homelearn.dto.student.dashboard.QViewQuestionBoardDto;
import project.homelearn.dto.student.dashboard.ViewQuestionBoardDto;
import project.homelearn.dto.teacher.dashboard.QQuestionTop5Dto;
import project.homelearn.dto.teacher.dashboard.QuestionTop5Dto;
import project.homelearn.entity.curriculum.Curriculum;

import java.util.List;

import static project.homelearn.entity.board.QQuestionBoard.questionBoard;
import static project.homelearn.entity.curriculum.QSubject.subject;

@RequiredArgsConstructor
public class QuestionBoardRepositoryImpl implements QuestionBoardRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<QuestionTop5Dto> findQuestionTop5(Curriculum curriculum) {
        return queryFactory
                .select(new QQuestionTop5Dto(questionBoard.id, questionBoard.subject.name, questionBoard.title, questionBoard.content, questionBoard.createdDate))
                .from(questionBoard)
                .leftJoin(questionBoard.subject, subject)
                .where(questionBoard.user.curriculum.eq(curriculum))
                .orderBy(questionBoard.createdDate.desc())
                .limit(5)
                .fetch();
    }

    @Override
    public List<QuestionTop5Dto> findQuestionTop5BySubjectId(Long subjectId) {
        return queryFactory
                .select(new QQuestionTop5Dto(questionBoard.id, questionBoard.subject.name, questionBoard.title, questionBoard.content, questionBoard.createdDate))
                .from(questionBoard)
                .where(questionBoard.subject.id.eq(subjectId))
                .orderBy(questionBoard.createdDate.desc())
                .limit(5)
                .fetch();
    }

    @Override
    public List<ViewQuestionBoardDto> findQuestionTop2(Curriculum curriculum){
        return queryFactory
                .select(new QViewQuestionBoardDto(questionBoard.id, questionBoard.subject.name, questionBoard.title, questionBoard.content, questionBoard.createdDate))
                .from(questionBoard)
                .leftJoin(questionBoard.subject, subject)
                .where(questionBoard.user.curriculum.eq(curriculum))
                .orderBy(questionBoard.createdDate.desc())
                .limit(2)
                .fetch();
    }
}