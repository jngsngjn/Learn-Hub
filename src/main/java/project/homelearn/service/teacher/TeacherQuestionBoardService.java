package project.homelearn.service.teacher;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import project.homelearn.dto.chatgpt.ChatGPTResponseDto;
import project.homelearn.dto.common.board.QuestionBoardCommentDto;
import project.homelearn.dto.common.board.QuestionBoardDetailDto;
import project.homelearn.dto.common.board.QuestionBoardDto;
import project.homelearn.dto.student.board.CommentWriteDto;
import project.homelearn.dto.teacher.AiCommentWriteDto;
import project.homelearn.dto.teacher.dashboard.QuestionTop5Dto;
import project.homelearn.entity.board.QuestionBoard;
import project.homelearn.entity.board.comment.QuestionBoardComment;
import project.homelearn.entity.curriculum.Curriculum;
import project.homelearn.entity.user.User;
import project.homelearn.repository.board.QuestionBoardCommentRepository;
import project.homelearn.repository.board.QuestionBoardRepository;
import project.homelearn.repository.curriculum.CurriculumRepository;
import project.homelearn.repository.user.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class TeacherQuestionBoardService {

    private final UserRepository userRepository;
    private final CurriculumRepository curriculumRepository;
    private final QuestionBoardRepository questionBoardRepository;
    private final QuestionBoardCommentRepository commentRepository;

    // 댓글 작성 = 답변달기
    public void writeComment(Long questionBoardId, String username, CommentWriteDto commentDto) {
        User user = userRepository.findByUsername(username);
        QuestionBoard questionBoard = questionBoardRepository.findById(questionBoardId).orElseThrow();

        QuestionBoardComment comment = new QuestionBoardComment();
        comment.setUser(user);
        comment.setQuestionBoard(questionBoard);
        comment.setContent(commentDto.getContent());
        commentRepository.save(comment);
    }

    // AI 자동응답 작성
    public void autoWriteComment(AiCommentWriteDto aiCommentWriteDto) {
        QuestionBoard questionBoard = questionBoardRepository.findById(aiCommentWriteDto.getQuestionBoardId()).orElseThrow();

        QuestionBoardComment comment = new QuestionBoardComment();
        comment.setUser(null);
        comment.setQuestionBoard(questionBoard);
        comment.setContent(aiCommentWriteDto.getContent());
        commentRepository.save(comment);
    }

    // 댓글 수정
    public boolean modifyComment(Long commentId, String username, CommentWriteDto commentDto) {
        QuestionBoardComment comment = commentRepository.findById(commentId).orElseThrow();
        String writer = comment.getUser().getUsername();
        if (!writer.equals(username)) {
            return false;
        }
        comment.setContent(commentDto.getContent());
        return true;
    }

    // 댓글 삭제
    public boolean deleteComment(Long questionBoardId, Long commentId, String username) {
        QuestionBoard board = questionBoardRepository.findById(questionBoardId).orElseThrow();
        String boardWriter = board.getUser().getUsername();

        // 게시글 주인은 모든 댓글 삭제 가능
        if (boardWriter.equals(username)) {
            commentRepository.deleteById(commentId);
            return true;
        }

        QuestionBoardComment comment = commentRepository.findById(commentId).orElseThrow();
        String commentWriter = comment.getUser().getUsername();
        if (!commentWriter.equals(username)) {
            return false;
        }
        commentRepository.deleteById(commentId);
        return true;
    }

    // 대댓글 작성
    public void writeReplyComment(Long commentId, String username, CommentWriteDto commentDto) {
        User user = userRepository.findByUsername(username);
        QuestionBoardComment parentComment = commentRepository.findById(commentId).orElseThrow();

        // 대댓글 깊이 확인
        if (parentComment.getParentComment() != null) {
            throw new IllegalArgumentException("대댓글의 깊이는 1로 제한됩니다.");
        }

        QuestionBoardComment reply = new QuestionBoardComment();
        reply.setUser(user);
        reply.setContent(commentDto.getContent());
        reply.setQuestionBoard(parentComment.getQuestionBoard());
        reply.setParentComment(parentComment);
        commentRepository.save(reply);
    }

    // 대댓글 수정
    public boolean modifyReplyComment(Long replyId, String username, CommentWriteDto commentDto) {
        QuestionBoardComment reply = commentRepository.findById(replyId).orElseThrow();
        String writer = reply.getUser().getUsername();
        if (!writer.equals(username)) {
            return false;
        }

        reply.setContent(commentDto.getContent());
        return true;
    }

    // 댓글 수 증가
    public void incrementCommentCount(Long questionBoardId){
        QuestionBoard questionBoard = questionBoardRepository.findById(questionBoardId).orElseThrow();
        questionBoard.setCommentCount(questionBoard.getCommentCount() + 1);
    }

    // 댓글 수 감소
    public void decrementCommentCount(Long questionBoardId){
        QuestionBoard questionBoard = questionBoardRepository.findById(questionBoardId).orElseThrow();
        questionBoard.setCommentCount(questionBoard.getCommentCount() - 1);
    }

    // 답변없는 게시물 탐색 스케줄링
    @Scheduled(fixedRate = 3600000) // 1시간마다 실행
    public void checkForUnansweredQuestions() {
        List<QuestionBoard> unansweredQuestions = findUnansweredQuestionsWithin12Hours();

        for (QuestionBoard question : unansweredQuestions) {
            try {
                // AI 답변 요청
                String aiResponse = getAIResponse(question.getContent());

                // 댓글 작성 DTO 생성 및 서비스 호출
                AiCommentWriteDto aiCommentWriteDto = new AiCommentWriteDto(question.getId(), "AI", aiResponse);
                autoWriteComment(aiCommentWriteDto);
            } catch (Exception e) {
                log.error("게시글 ID " + question.getId() + "에 대한 AI 응답 처리 중 오류 발생.", e);
            }
        }
        log.info("스케줄링 호출");
    }

    // AI 답변 받아오기
    private String getAIResponse(String prompt) {
        String url = "http://localhost:8080/bot/chat?prompt=" + prompt; // AnswerBotController 엔드포인트 URL
        log.info("request url: " + url);
        RestTemplate restTemplate = new RestTemplate();

        try {
            ResponseEntity<ChatGPTResponseDto> response = restTemplate.getForEntity(url, ChatGPTResponseDto.class);
            log.info("ai response: " + response.getBody());

            if (response.getBody() != null && !response.getBody().getChoices().isEmpty()) {
                log.info("ai response: " + response.getBody().getChoices());
                return response.getBody().getChoices().get(0).getMessage().getContent();
            }
        } catch (Exception e) {
            log.error("AI 응답을 가져오는 도중 오류가 발생했습니다.", e);
        }
        return "AI 응답을 가져오지 못했습니다.";
    }

    // 답변없는 게시글 불러오기
    public List<QuestionBoard> findUnansweredQuestionsWithin12Hours() {
        LocalDateTime twelveHoursAgo = LocalDateTime.now().minusHours(12);
        return questionBoardRepository.findByCreatedDateBeforeAndCommentsIsNull(twelveHoursAgo);
    }

    // 조회수 증가
    public void incrementViewCount(Long questionBoardId) {
        QuestionBoard questionBoard = questionBoardRepository.findById(questionBoardId).orElseThrow();
        questionBoard.setCommentCount(questionBoard.getViewCount() + 1);
    }

    // 글 상세보기
    public QuestionBoardDetailDto getQuestionBoard(Long questionBoardId) {
        QuestionBoard questionBoard = questionBoardRepository.findById(questionBoardId).orElseThrow();

        return new QuestionBoardDetailDto(
                questionBoard.getId(),
                questionBoard.getTitle(),
                questionBoard.getContent(),
                questionBoard.getViewCount(),
                questionBoard.getQuestionScraps().size(),
                questionBoard.getUser().getName(),
                questionBoard.getCreatedDate(),
                questionBoard.getCommentCount()
        );
    }

    // 댓글 뽑아오기
    public List<QuestionBoardCommentDto> getQuestionBoardComment(Long questionBoardId) {
        List<QuestionBoardComment> comments = commentRepository.findByQuestionBoardIdAndParentCommentIsNull(questionBoardId);

        return comments.stream()
                .map(this::convertToCommentDto)
                .collect(Collectors.toList());
    }

    // 댓글 Dto 변환
    public QuestionBoardCommentDto convertToCommentDto(QuestionBoardComment comment) {
        return new QuestionBoardCommentDto(
                comment.getId(),
                comment.getUser().getName(),
                comment.getUser().getImageName(),
                comment.getContent(),
                comment.getCreatedDate(),
                comment.getReplies().stream()
                        .map(this::convertToCommentDto)
                        .collect(Collectors.toList())
        );
    }

    //게시글 리스트
    public Page<QuestionBoardDto> getQuestionBoardList(String filterType, String subjectName, Curriculum curriculum, Pageable pageable) {
        if (filterType == null) {
            filterType = "default";
        }

        Page<QuestionBoard> questionBoards;

        // 필터링 타입에 따라 다른 쿼리 메소드 호출
        switch (filterType) {
            case "subject":
                questionBoards = questionBoardRepository.findBySubjectNameAndCurriculum(subjectName, curriculum, pageable);
                break;

            case "unanswered":
                questionBoards = questionBoardRepository.findByCommentsIsNullAndCurriculum(curriculum, pageable);
                break;

            case "subjectUnanswered":
                questionBoards = questionBoardRepository.findBySubjectNameAndCommentsIsNullAndCurriculum(subjectName, curriculum, pageable);
                break;

            default:
                questionBoards = questionBoardRepository.findByCreatedDateDesc(curriculum, pageable);
                break;
        }

        // Entity -> DTO 변환
        return questionBoards.map(this::convertToListDto);
    }

    private QuestionBoardDto convertToListDto(QuestionBoard questionBoard) {

        //선생님이 글을 달았는지 안달았는지 여부를 추가해야함
        boolean isCommentHere = questionBoardRepository.hasTeacherComment(questionBoard);

        return new QuestionBoardDto(
                questionBoard.getId(),
                questionBoard.getSubject().getName(),
                questionBoard.getTitle(),
                questionBoard.getUser().getName(),
                questionBoard.getContent(),
                questionBoard.getCreatedDate(),
                questionBoard.getCommentCount(),
                isCommentHere
        );
    }

    // 최근 질문 5개
    public List<QuestionTop5Dto> getQuestionTop5(String username) {
        Curriculum curriculum = curriculumRepository.findCurriculumByUsername(username);
        return questionBoardRepository.findQuestionTop5(curriculum);
    }
}