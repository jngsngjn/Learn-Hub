package project.homelearn.repository.board;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project.homelearn.entity.curriculum.Curriculum;
import project.homelearn.entity.teacher.TeacherBoard;
import project.homelearn.repository.board.querydsl.TeacherBoardRepositoryCustom;

public interface TeacherBoardRepository extends JpaRepository<TeacherBoard, Long>, TeacherBoardRepositoryCustom {

    //공지사항 상태 여부 확인 후 -> 강사 공지사항 목록 매핑(0=기본, 1=긴급) 생성날짜 기준으로 최신순 나열
    @Query("SELECT tb FROM TeacherBoard tb where tb.curriculum =:curriculum ORDER BY CASE WHEN tb.emergency = true THEN 0 ELSE 1 END, tb.createdDate DESC")
    Page<TeacherBoard> findTeacherBoardsBy(@Param("curriculum") Curriculum Curriculum, Pageable pageable);
}