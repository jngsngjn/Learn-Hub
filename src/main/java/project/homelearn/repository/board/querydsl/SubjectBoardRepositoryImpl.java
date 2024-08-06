package project.homelearn.repository.board.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import project.homelearn.dto.teacher.subject.QSubjectBoardTop5Dto;
import project.homelearn.dto.teacher.subject.QSubjectBoardViewDto;
import project.homelearn.dto.teacher.subject.SubjectBoardTop5Dto;
import project.homelearn.dto.teacher.subject.SubjectBoardViewDto;

import java.util.List;

import static project.homelearn.entity.curriculum.QSubjectBoard.subjectBoard;

@RequiredArgsConstructor
public class SubjectBoardRepositoryImpl implements SubjectBoardRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<SubjectBoardTop5Dto> findSubjectBoardTop5(Long subjectId) {
        return queryFactory
                .select(new QSubjectBoardTop5Dto(subjectBoard.id, subjectBoard.title, subjectBoard.content, subjectBoard.uploadFileName, subjectBoard.createdDate))
                .from(subjectBoard)
                .where(subjectBoard.subject.id.eq(subjectId))
                .orderBy(subjectBoard.createdDate.desc())
                .limit(5)
                .fetch();
    }

    @Override
    public SubjectBoardViewDto findSubjectBoard(Long boardId) {
        return queryFactory
                .select(new QSubjectBoardViewDto(
                        subjectBoard.id,
                        subjectBoard.title,
                        subjectBoard.content,
                        subjectBoard.viewCount,
                        subjectBoard.filePath,
                        subjectBoard.uploadFileName,
                        subjectBoard.createdDate))
                .from(subjectBoard)
                .where(subjectBoard.id.eq(boardId))
                .fetchOne();
    }
}