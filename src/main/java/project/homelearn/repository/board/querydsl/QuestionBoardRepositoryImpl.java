package project.homelearn.repository.board.querydsl;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import project.homelearn.dto.teacher.dashboard.QuestionTop5Dto;
import project.homelearn.entity.curriculum.Curriculum;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static project.homelearn.entity.board.QQuestionBoard.questionBoard;
import static project.homelearn.entity.curriculum.QSubject.subject;

@RequiredArgsConstructor
public class QuestionBoardRepositoryImpl implements QuestionBoardRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<QuestionTop5Dto> findQuestionTop5(Curriculum curriculum) {
        List<Tuple> tuples = queryFactory
                .select(questionBoard.id, questionBoard.subject.name, questionBoard.title, questionBoard.content, questionBoard.createdDate)
                .from(questionBoard)
                .leftJoin(questionBoard.subject, subject)
                .where(questionBoard.user.curriculum.eq(curriculum))
                .orderBy(questionBoard.createdDate.desc())
                .limit(5)
                .fetch();

        List<QuestionTop5Dto> result = new ArrayList<>();

        for (Tuple tuple : tuples) {
            Long questionId = tuple.get(questionBoard.id);
            String subjectName = tuple.get(questionBoard.subject.name);
            String title = tuple.get(questionBoard.title);
            String content = tuple.get(questionBoard.content);
            LocalDateTime createdDateTime = tuple.get(questionBoard.createdDate);
            LocalDate createdDate = createdDateTime.toLocalDate();

            if (subjectName == null || subjectName.isEmpty()) {
                QuestionTop5Dto dto = new QuestionTop5Dto(questionId, "기타", title, content, createdDate);
                result.add(dto);
            } else {
                QuestionTop5Dto dto = new QuestionTop5Dto(questionId, subjectName, title, content, createdDate);
                result.add(dto);
            }
        }
        return result;
    }
}