package project.homelearn.repository.board;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project.homelearn.entity.board.QuestionBoard;
import project.homelearn.entity.curriculum.Curriculum;
import project.homelearn.repository.board.querydsl.QuestionBoardRepositoryCustom;

import java.time.LocalDateTime;
import java.util.List;

public interface QuestionBoardRepository extends JpaRepository<QuestionBoard, Long>, QuestionBoardRepositoryCustom {

    List<QuestionBoard> findByCreatedDateBeforeAndCommentsIsNull(LocalDateTime dateTime);


    //키리큘럼 고려  1차

    //질문 게시판 페이지 리스트 -> 최신순
    @Query("select q from QuestionBoard q where q.user.curriculum = :curriculum order by q.createdDate desc")
    Page<QuestionBoard> findByCreatedDateDesc(@Param("curriculum")Curriculum curriculum, Pageable pageable);

    // 질문 게시판 필터링 1 : 커리큘럼 내에서 과목명 기준으로 최신순
    @Query("select q from QuestionBoard q join q.subject s where s.name = :subjectName and q.user.curriculum = :curriculum order by q.createdDate desc")
    Page<QuestionBoard> findBySubjectNameAndCurriculum(@Param("subjectName")String subjectName, @Param("curriculum")Curriculum curriculum, Pageable pageable);

    // 질문 게시판 필터링 2 : 커리큘럼 내에서 답변 없는 순
    @Query("select q from QuestionBoard q left join q.comments c where q.user.curriculum = :curriculum and c.id is null order by q.createdDate desc")
    Page<QuestionBoard> findByCommentsIsNullAndCurriculum(@Param("curriculum")Curriculum curriculum, Pageable pageable);

    // 질문 게시판 필터링 3 : 커리큘럼 내에서 과목명 + 답변 없는 순
    @Query("select q from QuestionBoard q left join q.comments c join q.subject s where s.name = :subjectName and q.user.curriculum = :curriculum and c.id is null order by q.createdDate desc")
    Page<QuestionBoard> findBySubjectNameAndCommentsIsNullAndCurriculum(@Param("subjectName")String subjectName, @Param("curriculum")Curriculum curriculum, Pageable pageable);

    // 최근 5개 리스트 : 커리큘럼 내에서 대시보드, 과목 상세보기에서 사용
    @Query("select q from QuestionBoard q where q.user.curriculum = :curriculum order by q.createdDate desc")
    List<QuestionBoard> findTop5ByCurriculumOrderByCreatedDateDesc(@Param("curriculum")Curriculum curriculum, Pageable pageable);


}