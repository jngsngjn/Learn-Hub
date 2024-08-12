package project.homelearn.service.teacher.homework;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import project.homelearn.dto.common.FileDto;
import project.homelearn.dto.teacher.dashboard.HomeworkStateDto;
import project.homelearn.dto.teacher.homework.*;
import project.homelearn.entity.curriculum.Curriculum;
import project.homelearn.entity.homework.AcceptFile;
import project.homelearn.entity.homework.Homework;
import project.homelearn.entity.homework.StudentHomework;
import project.homelearn.entity.student.Student;
import project.homelearn.entity.teacher.Teacher;
import project.homelearn.repository.curriculum.CurriculumRepository;
import project.homelearn.repository.homework.HomeworkRepository;
import project.homelearn.repository.homework.StudentHomeworkRepository;
import project.homelearn.repository.user.StudentRepository;
import project.homelearn.repository.user.TeacherRepository;
import project.homelearn.service.common.StorageService;
import project.homelearn.service.student.StudentNotificationService;

import java.time.LocalDateTime;
import java.util.List;

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
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final HomeworkRepository homeworkRepository;
    private final CurriculumRepository curriculumRepository;
    private final StudentHomeworkRepository studentHomeworkRepository;
    private final StudentNotificationService notificationService;

    public void enrollHomework(String username, HomeworkEnrollDto homeworkDto) {
        Teacher teacher = teacherRepository.findByUsernameAndCurriculum(username);
        Curriculum curriculum = teacher.getCurriculum();

        Homework homework = new Homework();
        homework.setTitle(homeworkDto.getTitle());
        homework.setDescription(homeworkDto.getDescription());
        homework.setDeadline(homeworkDto.getDeadLine());
        homework.setCurriculum(curriculum);

        Boolean requiredFile = homeworkDto.getRequiredFile();
        if (requiredFile) {
            homework.setRequiredFile(true);
            AcceptFile acceptFile = homeworkDto.getAcceptFile();
            if (acceptFile == null) {
                log.error("첨부 파일이 필수이지만 AcceptFile을 지정하지 않았습니다.");
                return;
            }
            homework.setAcceptFile(acceptFile);
        }

        MultipartFile file = homeworkDto.getFile();
        if (file != null) {
            String folderPath = storageService.getFolderPath(teacher, HOMEWORK);
            FileDto fileDto = storageService.uploadFile(file, folderPath);
            homework.setUploadFileName(fileDto.getOriginalFileName());
            homework.setStoreFileName(fileDto.getUploadFileName());
            homework.setFilePath(fileDto.getFilePath());
        }
        homeworkRepository.save(homework);

        // 학생들에게 알림
        List<Student> students = studentRepository.findAllByCurriculum(curriculum);
        notificationService.homeworkNotify(homework, students);
    }

    public boolean modifyHomework(Long homeworkId, String username, HomeworkModifyDto homeworkDto) {
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

    public boolean feedbackHomework(Long homeworkId, Long studentHomeworkId, String username, HomeworkFeedbackDto homeworkDto) {
        Homework homework = homeworkRepository.findHomeworkAndCurriculum(homeworkId);
        Curriculum curriculum = homework.getCurriculum();

        String writer = teacherRepository.findUsernameByCurriculum(curriculum);
        if (!writer.equals(username)) {
            return false;
        }

        StudentHomework studentHomework = studentHomeworkRepository.findById(studentHomeworkId).orElseThrow();
        studentHomework.setResponse(homeworkDto.getResponse());
        studentHomework.setResponseDate(LocalDateTime.now());
        return true;
    }

    public boolean modifyFeedbackHomework(Long homeworkId, Long studentHomeworkId, String username, HomeworkFeedbackDto homeworkDto) {
        return feedbackHomework(homeworkId, studentHomeworkId, username, homeworkDto);
    }

    public boolean deleteFeedbackHomework(Long homeworkId, Long studentHomeworkId, String username) {
        Homework homework = homeworkRepository.findHomeworkAndCurriculum(homeworkId);
        Curriculum curriculum = homework.getCurriculum();

        String writer = teacherRepository.findUsernameByCurriculum(curriculum);
        if (!writer.equals(username)) {
            return false;
        }

        studentHomeworkRepository.deleteById(studentHomeworkId);
        return true;
    }

    public HomeworkStateDto getHomeworkState(String username) {
        Curriculum curriculum = curriculumRepository.findCurriculumByUsername(username);
        Integer totalCount = studentRepository.findStudentCountByCurriculum(curriculum);
        return homeworkRepository.findHomeworkStateDto(curriculum, totalCount);
    }

    public Page<HomeworkTabDto> getHomeworks(String username, int page, int size, String status) {
        Curriculum curriculum = curriculumRepository.findCurriculumByUsername(username);
        PageRequest pageRequest = PageRequest.of(page, size);

        return homeworkRepository.findHomeworks(curriculum, pageRequest, status);
    }

    public HomeworkDetailDto getHomeworkDetail(String username, Long homeworkId) {
        Curriculum curriculum = curriculumRepository.findCurriculumByUsername(username);

        Integer totalCount = studentRepository.findStudentCountByCurriculum(curriculum);
        Long completedCount = homeworkRepository.findCompletedCount(homeworkId);
        long unsubmittedCount = totalCount - completedCount;

        return homeworkRepository.findHomeworkDetail(homeworkId, unsubmittedCount, curriculum);
    }

    public List<HomeworkSubmitListDto> getHomeworkSubmitList(String username, Long homeworkId) {
        Homework homework = homeworkRepository.findById(homeworkId).orElseThrow();
        Curriculum curriculum = homework.getCurriculum();
        String teacher = teacherRepository.findUsernameByCurriculum(curriculum);
        if (!username.equals(teacher)) {
            return null;
        }
        return homeworkRepository.findHomeworkSubmitList(homeworkId);
    }

    public List<StudentHomework> getAllStudentHomeworksByHomework(Homework homework) {
        return studentHomeworkRepository.findAllByHomework(homework);
    }

    public Homework getHomeworkById(Long homeworkId) {
        return homeworkRepository.findById(homeworkId).orElseThrow();
    }
}