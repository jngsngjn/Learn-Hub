package project.homelearn.repository.board.querydsl;

import project.homelearn.dto.student.dashboard.ViewQuestionBoardDto;
import project.homelearn.dto.teacher.dashboard.QuestionTop5Dto;
import project.homelearn.entity.curriculum.Curriculum;

import java.util.List;

public interface QuestionBoardRepositoryCustom {

    List<QuestionTop5Dto> findQuestionTop5(Curriculum curriculum);

    List<ViewQuestionBoardDto> findQuestionTop2(Curriculum curriculum);

    List<QuestionTop5Dto> findQuestionTop5BySubjectId(Long subjectId);
}