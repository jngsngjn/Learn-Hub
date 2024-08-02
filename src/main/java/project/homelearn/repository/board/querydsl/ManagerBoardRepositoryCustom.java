package project.homelearn.repository.board.querydsl;

import project.homelearn.dto.teacher.dashboard.ManagerBoardDto;

import java.util.List;

public interface ManagerBoardRepositoryCustom {

    List<ManagerBoardDto> findManagerBoardRecent4();
}