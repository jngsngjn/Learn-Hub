package project.homelearn.repository.board.querydsl;

import project.homelearn.dto.teacher.board.TeacherBoardDto;
import project.homelearn.dto.teacher.dashboard.ManagerBoardDto;

import java.util.List;

public interface TeacherBoardRepositoryCustom {

    List<TeacherBoardDto> findTeacherBoardRecent4();
}
