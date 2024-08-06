package project.homelearn.repository.board.querydsl;

import project.homelearn.dto.teacher.subject.SubjectBoardTop5Dto;
import project.homelearn.dto.teacher.subject.SubjectBoardViewDto;

import java.util.List;

public interface SubjectBoardRepositoryCustom {

    List<SubjectBoardTop5Dto> findSubjectBoardTop5(Long subjectId);

    SubjectBoardViewDto findSubjectBoard(Long boardId);
}