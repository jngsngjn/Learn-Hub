package project.homelearn.service.teacher;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import project.homelearn.dto.common.FileDto;
import project.homelearn.dto.teacher.homework.HomeworkEnrollDto;
import project.homelearn.entity.curriculum.Curriculum;
import project.homelearn.entity.homework.Homework;
import project.homelearn.entity.teacher.Teacher;
import project.homelearn.repository.homework.HomeworkRepository;
import project.homelearn.repository.user.TeacherRepository;
import project.homelearn.service.common.StorageService;

import static project.homelearn.config.storage.FolderType.HOMEWORK;

/**
 * Author : 정성진
 */
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class TeacherHomeworkService {

    private final StorageService storageService;
    private final TeacherRepository teacherRepository;
    private final HomeworkRepository homeworkRepository;

    public void enrollHomework(String username, HomeworkEnrollDto homeworkDto) {
        Teacher teacher = teacherRepository.findByUsernameAndCurriculum(username);
        Curriculum curriculum = teacher.getCurriculum();

        Homework homework = new Homework();
        homework.setTitle(homeworkDto.getTitle());
        homework.setDescription(homeworkDto.getDescription());
        homework.setDeadline(homeworkDto.getDeadLine());
        homework.setCurriculum(curriculum);

        MultipartFile file = homeworkDto.getFile();
        if (file != null) {
            String folderPath = storageService.getFolderPath(teacher, HOMEWORK);
            FileDto fileDto = storageService.uploadFile(file, folderPath);
            homework.setUploadFileName(fileDto.getOriginalFileName());
            homework.setStoreFileName(fileDto.getUploadFileName());
            homework.setFilePath(fileDto.getFilePath());
        }
        homeworkRepository.save(homework);
    }

    public boolean modifyHomework(Long homeworkId, String username, HomeworkEnrollDto homeworkDto) {
        Homework homework = homeworkRepository.findHomeworkAndCurriculum(homeworkId);
        Curriculum curriculum = homework.getCurriculum();

        String writer = teacherRepository.findUsernameByCurriculum(curriculum);
        if (!writer.equals(username)) {
            return false;
        }

        homework.setTitle(homeworkDto.getTitle());
        homework.setDescription(homeworkDto.getDescription());
        homework.setDeadline(homeworkDto.getDeadLine());

        MultipartFile file = homeworkDto.getFile();
        if (file != null) {
            String previousFile = homework.getFilePath();
            if (previousFile != null) {
                storageService.deleteFile(previousFile);
            }

            Teacher teacher = teacherRepository.findByUsername(username);
            String folderPath = storageService.getFolderPath(teacher, HOMEWORK);

            FileDto fileDto = storageService.uploadFile(file, folderPath);
            homework.setUploadFileName(fileDto.getOriginalFileName());
            homework.setStoreFileName(fileDto.getUploadFileName());
            homework.setFilePath(fileDto.getFilePath());
        }
        // 기존 사진이 있었을 때 삭제하는 로직 추가해야 함
        return true;
    }

    public boolean deleteHomework(Long homeworkId, String username) {
        Homework homework = homeworkRepository.findHomeworkAndCurriculum(homeworkId);
        Curriculum curriculum = homework.getCurriculum();

        String writer = teacherRepository.findUsernameByCurriculum(curriculum);
        if (!writer.equals(username)) {
            return false;
        }

        String file = homework.getFilePath();
        if (file != null) {
            storageService.deleteFile(file);
        }

        homeworkRepository.deleteById(homeworkId);
        return true;
    }
}