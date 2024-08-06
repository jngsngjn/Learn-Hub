package project.homelearn.repository.board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project.homelearn.entity.board.scrap.QuestionScrap;

public interface QuestionScrapRepository extends JpaRepository<QuestionScrap, Long> {

    // 질문 게시판에 내가 스크랩을 했는지 안했는지를 판단
    @Query("select count(qs) > 0 from QuestionScrap qs where qs.user.username =:username and qs.questionBoard.id =:questionBoardId")
    boolean existByUserNameAndQuestionBoardId(@Param("username") String username, @Param("questionBoardId") Long questionBoardId);

    // 사용자 username과 questionBoardId를 통해 스크랩 삭제
    void deleteByUserNameAndQuestionBoardId(String username, Long questionBoardId);
}