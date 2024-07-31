package project.homelearn.service.teacher;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.homelearn.dto.teacher.board.TeacherBoardCreateDto;
import project.homelearn.entity.curriculum.Curriculum;
import project.homelearn.entity.teacher.TeacherBoard;
import project.homelearn.repository.board.TeacherBoardRepository;
import project.homelearn.repository.user.TeacherRepository;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class TeacherBoardService {

    private final TeacherBoardRepository teacherBoardRepository;
    private final TeacherRepository teacherRepository;

    public boolean createTeacherBoard(String username, TeacherBoardCreateDto teacherBoardCreateDto) {
        try {
            Curriculum curriculum = teacherRepository.findByUsername(username).getCurriculum();
            TeacherBoard teacherBoard = new TeacherBoard();
            teacherBoard.setTitle(teacherBoardCreateDto.getTitle());
            teacherBoard.setContent(teacherBoardCreateDto.getContent());
            teacherBoard.setEmergency(teacherBoardCreateDto.getEmergency());
            teacherBoard.setCurriculum(curriculum);
            teacherBoardRepository.save(teacherBoard);
            return true;
        } catch (Exception e) {
            log.error("Error occurred while creating teacher board", e);
            return false;
        }
    }
}
