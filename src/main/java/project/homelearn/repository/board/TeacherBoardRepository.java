package project.homelearn.repository.board;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import project.homelearn.entity.teacher.TeacherBoard;

public interface TeacherBoardRepository extends JpaRepository<TeacherBoard, Long> {


// 조회용
//    @Query("SELECT tb FROM TeacherBoard tb ORDER BY CASE WHEN tb.emergency = true THEN 0 ELSE 1 END, tb.createdDate DESC")
//    Page<TeacherBoard> findByTeacherBoardsBy(Pageable pageable);
}
