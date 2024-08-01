package project.homelearn.service.teacher;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import project.homelearn.dto.common.FileDto;
import project.homelearn.dto.teacher.subject.SubjectEnrollDto;
import project.homelearn.dto.teacher.subject.SubjectModifyDto;
import project.homelearn.entity.curriculum.Curriculum;
import project.homelearn.entity.curriculum.Subject;
import project.homelearn.entity.teacher.Teacher;
import project.homelearn.repository.curriculum.SubjectRepository;
import project.homelearn.repository.user.TeacherRepository;
import project.homelearn.service.common.StorageService;

import static project.homelearn.config.storage.FolderType.SUBJECT;

/**
 * Author : 정성진
 */
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class SubjectService {

    private final StorageService storageService;
    private final TeacherRepository teacherRepository;
    private final SubjectRepository subjectRepository;

    public void createSubject(String username, SubjectEnrollDto subjectDto) {
        Teacher teacher = teacherRepository.findByUsernameAndCurriculum(username);

        Subject subject = new Subject();
        subject.setName(subjectDto.getName());
        subject.setCurriculum(teacher.getCurriculum());
        subject.setDescription(subjectDto.getDescription());

        MultipartFile image = subjectDto.getImage();
        if (image != null) {
            String folderPath = storageService.getFolderPath(teacher, SUBJECT);
            FileDto fileDto = storageService.uploadFile(image, folderPath);
            subject.setImageName(fileDto.getUploadFileName());
            subject.setImagePath(fileDto.getFilePath());
        }
        subjectRepository.save(subject);
    }

    public boolean modifySubject(Long subjectId, String username, SubjectModifyDto subjectDto) {
        Subject subject = subjectRepository.findSubjectAndCurriculum(subjectId);
        Curriculum curriculum = subject.getCurriculum();

        String writer = teacherRepository.findUsernameByCurriculum(curriculum);
        if (!writer.equals(username)) {
            return false;
        }

        subject.setName(subjectDto.getName());
        subject.setDescription(subjectDto.getDescription());

        MultipartFile image = subjectDto.getImage();
        String previousImage = subject.getImagePath();

        // 사진 첨부 O
        if (image != null) {
            // 사진 수정 시 기존 사진이 있다면 삭제
            if (previousImage != null) {
                storageService.deleteFile(previousImage);
            }

            Teacher teacher = teacherRepository.findByUsername(username);
            String folderPath = storageService.getFolderPath(teacher, SUBJECT);

            FileDto fileDto = storageService.uploadFile(image, folderPath);
            subject.setImageName(fileDto.getUploadFileName());
            subject.setImagePath(fileDto.getFilePath());
        }

        // 기본 이미지 사용 시
        else if (subjectDto.isUseDefaultImage()) {
            storageService.deleteFile(previousImage);
            subject.setImageName(null);
            subject.setImagePath(null);
        }
        return true;
    }

    public boolean deleteSubject(Long subjectId, String username) {
        Subject subject = subjectRepository.findSubjectAndCurriculum(subjectId);
        Curriculum curriculum = subject.getCurriculum();

        String writer = teacherRepository.findUsernameByCurriculum(curriculum);
        if (!writer.equals(username)) {
            return false;
        }

        String image = subject.getImagePath();
        if (image != null) {
            storageService.deleteFile(image);
        }

        subjectRepository.deleteById(subjectId);
        return true;
    }
}