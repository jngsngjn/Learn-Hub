package project.homelearn.service.student;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import project.homelearn.dto.common.FileDto;
import project.homelearn.dto.student.homework.HomeworkSubmitDto;
import project.homelearn.dto.student.homework.HomeworkUpdateDto;
import project.homelearn.entity.homework.Homework;
import project.homelearn.entity.homework.StudentHomework;
import project.homelearn.entity.student.Student;
import project.homelearn.repository.homework.HomeworkRepository;
import project.homelearn.repository.homework.StudentHomeworkRepository;
import project.homelearn.repository.user.StudentRepository;
import project.homelearn.service.common.StorageService;

import java.time.LocalDateTime;

import static project.homelearn.config.storage.FolderType.HOMEWORK;

/**
 * Author : 동재완
 */
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class StudentHomeworkService {

    private final StorageService storageService;
    private final StudentRepository studentRepository;
    private final HomeworkRepository homeworkRepository;
    private final StudentHomeworkRepository studentHomeworkRepository;

    public boolean createHomework(String username, HomeworkSubmitDto homeWorkSubmitDto) {
        try {
            Student student = studentRepository.findByUsername(username);
            if (student == null) {
                throw new RuntimeException("Student not found");
            }

            Homework homework = homeworkRepository.findHomeworkAndCurriculum(homeWorkSubmitDto.getHomeworkId());
            if (homework == null) {
                throw new RuntimeException("Homework not found");
            }
            log.debug("Homework found: {}", homework);

            StudentHomework studentHomework = new StudentHomework();
            studentHomework.setUser(student);
            studentHomework.setDescription(homeWorkSubmitDto.getDescription());
            studentHomework.setHomework(homework);

            MultipartFile file = homeWorkSubmitDto.getFile();
            if (file != null && !file.isEmpty()) {
                String folderPath = storageService.getFolderPath(student, HOMEWORK);
                FileDto fileDto = storageService.uploadFile(file, folderPath);
                studentHomework.setUploadFileName(fileDto.getOriginalFileName());
                studentHomework.setStoreFileName(fileDto.getUploadFileName());
                studentHomework.setFilePath(fileDto.getFilePath());
            }

            studentHomeworkRepository.save(studentHomework);
            return true;
        } catch (Exception e) {
            log.error("Error creating homework", e);
            return false;
        }
    }

    public boolean updateHomework(Long homeworkId, String username, HomeworkUpdateDto homeWorkUpdateDto) {
        try {
            StudentHomework homework = studentHomeworkRepository.findById(homeworkId)
                    .orElseThrow(() -> new RuntimeException("Error getting homework" + homeworkId));

            LocalDateTime deadline = homework.getHomework().getDeadline();
            if (LocalDateTime.now().isAfter(deadline)) {
                throw new RuntimeException("Cannot update homework after the deadline:" + homeworkId);
            }

            homework.setDescription(homeWorkUpdateDto.getDescription());

            MultipartFile file = homeWorkUpdateDto.getFile();
            String previousFilePath = homework.getFilePath();

            if (homeWorkUpdateDto.isUseDefaultFile() || file != null && !file.isEmpty()) {
                if (previousFilePath != null) {
                    storageService.deleteFile(previousFilePath);
                    homework.setUploadFileName(null);
                    homework.setStoreFileName(null);
                    homework.setFilePath(null);
                }
            }

            Student student = studentRepository.findByUsername(username);
            if (file != null && !file.isEmpty()) {
                String folderPath = storageService.getFolderPath(student, HOMEWORK);
                FileDto fileDto = storageService.uploadFile(file, folderPath);
                homework.setUploadFileName(fileDto.getOriginalFileName());
                homework.setStoreFileName(fileDto.getUploadFileName());
                homework.setFilePath(fileDto.getFilePath());
            }

            studentHomeworkRepository.save(homework);
            return true;
        } catch (Exception e) {
            log.error("Error updating homework", e);
            return false;
        }
    }

    public boolean deleteHomework(Long homeworkId) {
        try {
            StudentHomework homework = studentHomeworkRepository.findById(homeworkId)
                    .orElseThrow(() -> new RuntimeException("Error getting homework" + homeworkId));

            LocalDateTime deadline = homework.getHomework().getDeadline();
            if (LocalDateTime.now().isAfter(deadline)) {
                throw new RuntimeException("Cannot delete homework after the deadline:" + homeworkId);
            }

            String file = homework.getFilePath();
            if (file != null) {
                storageService.deleteFile(file);
            }

            studentHomeworkRepository.deleteById(homeworkId);
            return true;
        } catch (Exception e) {
            log.error("Error deleting homework", e);
            return false;
        }
    }
}