package project.homelearn.repository.board;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import project.homelearn.entity.board.QuestionBoard;
import project.homelearn.repository.board.querydsl.QuestionBoardRepositoryCustom;

import java.time.LocalDateTime;
import java.util.List;

public interface QuestionBoardRepository extends JpaRepository<QuestionBoard, Long>, QuestionBoardRepositoryCustom {

    List<QuestionBoard> findByCreatedDateBeforeAndCommentsIsNull(LocalDateTime dateTime);

    //질문 게시판 페이지 리스트 -> 최신순
    @Query("select q from QuestionBoard q order by q.createdDate desc ")
    Page<QuestionBoard> findByCreatedDateDesc(Pageable pageable);

    //질문 게시판 필터링 1 : 과목명 기준으로 최신순
    @Query("select q from QuestionBoard q join fetch q.subject s where s.name =:subjectName order by q.createdDate desc")
    Page<QuestionBoard> findBySubjectName(String subjectName, Pageable pageable);

    //질문 게시판 필터링 2 : 답변없는 순
    @Query("select q from QuestionBoard q left join q.comments c where c.id is null order by q.createdDate desc")
    Page<QuestionBoard> findByCommentsIsNull(Pageable pageable);

    //질문 게시판 필터링 3: 과목명 + 답변 없는 순
    @Query("select q from QuestionBoard q left join q.comments c join q.subject s where s.name = :subjectName and c.id is null order by q.createdDate desc")
    Page<QuestionBoard> findBySubjectNameAndCommentsIsNull(String subjectName, Pageable pageable);
}