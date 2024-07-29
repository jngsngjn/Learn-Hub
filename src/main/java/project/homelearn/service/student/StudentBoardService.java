package project.homelearn.service.student;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.homelearn.dto.student.board.CommentWriteDto;
import project.homelearn.dto.student.board.FreeBoardWriteDto;
import project.homelearn.entity.board.FreeBoard;
import project.homelearn.entity.board.comment.FreeBoardComment;
import project.homelearn.entity.student.Student;
import project.homelearn.entity.user.User;
import project.homelearn.repository.board.FreeBoardCommentRepository;
import project.homelearn.repository.board.FreeBoardRepository;
import project.homelearn.repository.user.StudentRepository;
import project.homelearn.repository.user.UserRepository;

/**
 * Author : 정성진
 */
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class StudentBoardService {

    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final FreeBoardRepository boardRepository;
    private final FreeBoardCommentRepository commentRepository;

    public void writeBoard(String username, FreeBoardWriteDto boardDto) {
        Student student = studentRepository.findByUsername(username);

        FreeBoard board = new FreeBoard();
        board.setUser(student);
        board.setTitle(boardDto.getTitle());
        board.setContent(boardDto.getContent());
        boardRepository.save(board);
    }

    public boolean modifyBoard(Long boardId, String username, FreeBoardWriteDto boardDto) {
        FreeBoard board = boardRepository.findById(boardId).orElseThrow();
        User user = board.getUser();
        if (!user.getUsername().equals(username)) {
            return false;
        }
        board.setTitle(boardDto.getTitle());
        board.setContent(boardDto.getContent());
        return true;
    }

    public boolean deleteBoard(Long boardId, String username) {
        FreeBoard board = boardRepository.findById(boardId).orElseThrow();
        String writer = board.getUser().getUsername();

        if (!writer.equals(username)) {
            return false;
        }

        boardRepository.deleteById(boardId);
        return true;
    }

    public void writeComment(Long boardId, String username, CommentWriteDto commentDto) {
        User user = userRepository.findByUsername(username);
        FreeBoard board = boardRepository.findById(boardId).orElseThrow();

        FreeBoardComment comment = new FreeBoardComment();
        comment.setUser(user);
        comment.setFreeBoard(board);
        comment.setContent(commentDto.getContent());
        commentRepository.save(comment);
    }

    public boolean modifyComment(Long commentId, String username, CommentWriteDto commentDto) {
        FreeBoardComment comment = commentRepository.findById(commentId).orElseThrow();
        String writer = comment.getUser().getUsername();
        if (!writer.equals(username)) {
            return false;
        }
        comment.setContent(commentDto.getContent());
        return true;
    }

    public boolean deleteComment(Long boardId, Long commentId, String username) {
        FreeBoard board = boardRepository.findById(boardId).orElseThrow();
        String boardWriter = board.getUser().getUsername();

        // 게시글 주인은 모든 댓글 삭제 가능
        if (boardWriter.equals(username)) {
            commentRepository.deleteById(commentId);
            return true;
        }

        FreeBoardComment comment = commentRepository.findById(commentId).orElseThrow();
        String commentWriter = comment.getUser().getUsername();
        if (!commentWriter.equals(username)) {
            return false;
        }
        commentRepository.deleteById(commentId);
        return true;
    }

    public void writeReplyComment(Long commentId, String username, CommentWriteDto commentDto) {
        User user = userRepository.findByUsername(username);
        FreeBoardComment parentComment = commentRepository.findById(commentId).orElseThrow();

        // 대댓글 깊이 확인
        if (parentComment.getParentComment() != null) {
            throw new IllegalArgumentException("대댓글의 깊이는 1로 제한됩니다.");
        }

        FreeBoardComment reply = new FreeBoardComment();
        reply.setUser(user);
        reply.setContent(commentDto.getContent());
        reply.setFreeBoard(parentComment.getFreeBoard());
        reply.setParentComment(parentComment);
        commentRepository.save(reply);
    }

    public boolean modifyReplyComment(Long replyId, String username, CommentWriteDto commentDto) {
        FreeBoardComment reply = commentRepository.findById(replyId).orElseThrow();
        String writer = reply.getUser().getUsername();
        if (!writer.equals(username)) {
            return false;
        }

        reply.setContent(commentDto.getContent());
        return true;
    }
}