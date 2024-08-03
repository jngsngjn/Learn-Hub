package project.homelearn.repository.board;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import project.homelearn.entity.manager.ManagerBoard;
import project.homelearn.repository.board.querydsl.ManagerBoardRepositoryCustom;

public interface ManagerBoardRepository extends JpaRepository<ManagerBoard, Long>, ManagerBoardRepositoryCustom {

    //긴급 공지사항 사용 여부 확인하여 매니저 게시판 목록 매핑(0은 기본, 1은 긴급), 생성날짜 최신순으로 나열
    @Query("SELECT mb FROM ManagerBoard mb ORDER BY CASE WHEN mb.emergency = true THEN 0 ELSE 1 END, mb.createdDate DESC")
    Page<ManagerBoard> findManagerBoardsBy(Pageable pageable);
}