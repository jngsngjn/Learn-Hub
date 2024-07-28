package project.homelearn.service.student;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.homelearn.dto.student.board.StudentBoardWriteDto;
import project.homelearn.entity.board.FreeBoard;
import project.homelearn.entity.student.Student;
import project.homelearn.entity.user.User;
import project.homelearn.repository.board.FreeBoardRepository;
import project.homelearn.repository.user.StudentRepository;

/**
 * Author : 정성진
 */
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class StudentBoardService {

    private final StudentRepository studentRepository;
    private final FreeBoardRepository boardRepository;

    public void writeBoard(String username, StudentBoardWriteDto boardDto) {
        Student student = studentRepository.findByUsername(username);

        FreeBoard board = new FreeBoard();
        board.setUser(student);
        board.setTitle(boardDto.getTitle());
        board.setContent(boardDto.getContent());
        boardRepository.save(board);
    }

    public boolean modifyBoard(Long boardId, String username, StudentBoardWriteDto boardDto) {
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
}