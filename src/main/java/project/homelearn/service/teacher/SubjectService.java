package project.homelearn.service.teacher;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import project.homelearn.dto.common.FileDto;
import project.homelearn.dto.teacher.subject.SubjectCreateDto;
import project.homelearn.entity.curriculum.Subject;
import project.homelearn.entity.teacher.Teacher;
import project.homelearn.repository.curriculum.SubjectRepository;
import project.homelearn.repository.user.TeacherRepository;
import project.homelearn.service.common.StorageService;

import static project.homelearn.config.storage.FolderType.SUBJECT;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class SubjectService {

    private final StorageService storageService;
    private final TeacherRepository teacherRepository;
    private final SubjectRepository subjectRepository;

    public void createSubject(String username, SubjectCreateDto subjectDto) {
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
}