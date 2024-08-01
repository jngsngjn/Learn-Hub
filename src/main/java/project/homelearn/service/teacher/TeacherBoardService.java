package project.homelearn.service.teacher;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import project.homelearn.config.storage.FolderType;
import project.homelearn.dto.common.FileDto;
import project.homelearn.dto.teacher.board.TeacherBoardCreateDto;
import project.homelearn.entity.curriculum.Curriculum;
import project.homelearn.entity.teacher.Teacher;
import project.homelearn.entity.teacher.TeacherBoard;
import project.homelearn.entity.user.User;
import project.homelearn.repository.board.TeacherBoardRepository;
import project.homelearn.repository.user.TeacherRepository;
import project.homelearn.service.common.StorageService;


/**
 * Author : 김수정
 */

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class TeacherBoardService {

    private final TeacherBoardRepository teacherBoardRepository;
    private final TeacherRepository teacherRepository;
    private final StorageService storageService;

    public boolean createTeacherBoard(String username, TeacherBoardCreateDto teacherBoardCreateDto) {

        try {
            Curriculum curriculum = teacherRepository.findByUsername(username).getCurriculum();
            Teacher teacher = teacherRepository.findByUsername(username);

            MultipartFile file = teacherBoardCreateDto.getUploadFile();

            TeacherBoard teacherBoard = new TeacherBoard();
            teacherBoard.setTitle(teacherBoardCreateDto.getTitle());
            teacherBoard.setContent(teacherBoardCreateDto.getContent());
            teacherBoard.setEmergency(teacherBoardCreateDto.getEmergency());
            teacherBoard.setCurriculum(curriculum);

            if (file != null) {
                String folderPath = storageService.getFolderPath(teacher, FolderType.SUBJECT);
                 FileDto fileDto = storageService.uploadFile(file, folderPath);
                teacherBoard.setFilePath(fileDto.getFilePath());
                teacherBoard.setStoreFileName(fileDto.getUploadFileName());
            }

            teacherBoardRepository.save(teacherBoard);

            return true;

        } catch (Exception e) {
            log.error("Error occurred while creating teacher board", e);
            return false;
        }
    }

    // 공지 수정
    public boolean modifyTeacherBoard(Long boardId, String username, TeacherBoardCreateDto teacherBoardCreateDto) {
        TeacherBoard teacherBoard = teacherBoardRepository.findById(boardId).orElseThrow();
        User teacher = teacherRepository.findByUsername(username);
        MultipartFile file = teacherBoardCreateDto.getUploadFile();

        if(!teacher.getUsername().equals(username)) {
            return false;
        }

            if(file != null) {
                if (teacherBoard.getFilePath() != null) {
                    storageService.deleteFile(teacherBoard.getFilePath());
                }
                    String folderPath = storageService.getFolderPath(teacher, FolderType.SUBJECT);
                    FileDto fileDto = storageService.uploadFile(file, folderPath);
                    teacherBoard.setFilePath(fileDto.getFilePath());
                    teacherBoard.setStoreFileName(fileDto.getUploadFileName());
            }

        teacherBoard.setTitle(teacherBoardCreateDto.getTitle());
        teacherBoard.setContent(teacherBoardCreateDto.getContent());
        teacherBoard.setEmergency(teacherBoardCreateDto.getEmergency());

        teacherBoardRepository.save(teacherBoard);

        return true;
    }

}
