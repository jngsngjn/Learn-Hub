package project.homelearn.service.student;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;
import project.homelearn.dto.common.FileDto;
import project.homelearn.dto.student.board.CommentWriteDto;
import project.homelearn.dto.student.board.QuestionBoardWriteDto;
import project.homelearn.entity.board.QuestionBoard;
import project.homelearn.entity.board.comment.QuestionBoardComment;
import project.homelearn.entity.curriculum.Subject;
import project.homelearn.entity.student.Student;
import project.homelearn.entity.user.User;
import project.homelearn.repository.board.QuestionBoardCommentRepository;
import project.homelearn.repository.board.QuestionBoardRepository;
import project.homelearn.repository.curriculum.SubjectRepository;
import project.homelearn.repository.user.StudentRepository;
import project.homelearn.repository.user.UserRepository;
import project.homelearn.service.common.StorageService;

import java.security.Principal;

import static project.homelearn.config.storage.FolderType.FREE_BOARD;
import static project.homelearn.config.storage.FolderType.QUESTION_BOARD;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class StudentQuestionBoardService {

    private final QuestionBoardRepository questionBoardRepository;
    private final StudentRepository studentRepository;
    private final StorageService storageService;
    private final SubjectRepository subjectRepository;
    private final UserRepository userRepository;
    private final QuestionBoardCommentRepository commentRepository;

    //글 작성
    public void writeQuestionBoard(String username, QuestionBoardWriteDto questionBoardWriteDto) {

        Student student = studentRepository.findByUsername(username);

        QuestionBoard questionBoard = new QuestionBoard();

        questionBoard.setUser(student);
        questionBoard.setTitle(questionBoardWriteDto.getTitle());
        questionBoard.setContent(questionBoardWriteDto.getContent());

        Subject subject = subjectRepository.findById(questionBoardWriteDto.getSubjectId()).orElse(null);
        questionBoard.setSubject(subject);

        MultipartFile image = questionBoardWriteDto.getImage();
        if (image != null) {
            String folderPath = storageService.getFolderPath(student, QUESTION_BOARD);
            FileDto fileDto = storageService.uploadFile(image, folderPath);
            questionBoard.setImageName(fileDto.getUploadFileName());
            questionBoard.setImagePath(fileDto.getFilePath());
        }

        questionBoardRepository.save(questionBoard);
    }

    //글 삭제
    public boolean deleteQuestionBoard(Long questionBoardId, String username){
        QuestionBoard questionBoard = questionBoardRepository.findById(questionBoardId).orElseThrow();
        String writer = questionBoard.getUser().getUsername();

        if (!writer.equals(username)) {
            return false;
        }

        String image = questionBoard.getImagePath();
        if (image != null) {
            storageService.deleteFile(image);
        }

        questionBoardRepository.deleteById(questionBoardId);
        return true;
    }

    //글 수정
    public boolean modifyBoard(Long questionBoardId, String username, QuestionBoardWriteDto questionBoardWriteDto) {
        QuestionBoard questionBoard = questionBoardRepository.findById(questionBoardId).orElseThrow();
        User student = questionBoard.getUser();
        if (!student.getUsername().equals(username)) {
            return false;
        }
        questionBoard.setTitle(questionBoardWriteDto.getTitle());
        questionBoard.setContent(questionBoardWriteDto.getContent());

        MultipartFile image = questionBoardWriteDto.getImage();
        if (image != null) {
            // 사진 수정 시 기존 사진이 있다면 삭제
            String previousImage = questionBoard.getImagePath();
            if (previousImage != null) {
                storageService.deleteFile(previousImage);
            }

            String folderPath = storageService.getFolderPath(student, QUESTION_BOARD);
            FileDto fileDto = storageService.uploadFile(image, folderPath);
            questionBoard.setImageName(fileDto.getUploadFileName());
            questionBoard.setImagePath(fileDto.getFilePath());
        }

        return true;
    }

    //댓글 작성
    public void writeComment(Long questionBoardId, String username, CommentWriteDto commentDto) {
        User user = userRepository.findByUsername(username);
        QuestionBoard questionBoard = questionBoardRepository.findById(questionBoardId).orElseThrow();

        QuestionBoardComment comment = new QuestionBoardComment();
        comment.setUser(user);
        comment.setQuestionBoard(questionBoard);
        comment.setContent(commentDto.getContent());
        commentRepository.save(comment);
    }

    //댓글 수정
    public boolean modifyComment(Long commentId, String username, CommentWriteDto commentDto) {
        QuestionBoardComment comment = commentRepository.findById(commentId).orElseThrow();
        String writer = comment.getUser().getUsername();
        if (!writer.equals(username)) {
            return false;
        }
        comment.setContent(commentDto.getContent());
        return true;
    }

    //댓글 삭제
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

    //대댓글 작성
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

    //대댓글 수정
    public boolean modifyReplyComment(Long replyId, String username, CommentWriteDto commentDto) {
        QuestionBoardComment reply = commentRepository.findById(replyId).orElseThrow();
        String writer = reply.getUser().getUsername();
        if (!writer.equals(username)) {
            return false;
        }

        reply.setContent(commentDto.getContent());
        return true;
    }

    //댓글 수 증가
    public void incrementCommentCount(Long questionBoardId){
        QuestionBoard questionBoard = questionBoardRepository.findById(questionBoardId).orElseThrow();
        questionBoard.setCommentCount(questionBoard.getCommentCount() + 1);
    }

    //댓글 수 감소
    public void decrementCommentCount(Long questionBoardId){
        QuestionBoard questionBoard = questionBoardRepository.findById(questionBoardId).orElseThrow();
        questionBoard.setCommentCount(questionBoard.getCommentCount() - 1);
    }


    //질문 게시판 글 스크랩 = 나도 궁금해

    //조회수 증가

    //글 상세보기

    //게시글 리스트
}
