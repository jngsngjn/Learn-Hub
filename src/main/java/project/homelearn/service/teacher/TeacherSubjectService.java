package project.homelearn.service.teacher;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import project.homelearn.dto.common.FileDto;
import project.homelearn.dto.teacher.dashboard.QuestionTop5Dto;
import project.homelearn.dto.teacher.lecture.LectureListDto;
import project.homelearn.dto.teacher.subject.*;
import project.homelearn.entity.curriculum.Curriculum;
import project.homelearn.entity.curriculum.Subject;
import project.homelearn.entity.curriculum.SubjectBoard;
import project.homelearn.entity.teacher.Teacher;
import project.homelearn.repository.board.QuestionBoardRepository;
import project.homelearn.repository.board.SubjectBoardRepository;
import project.homelearn.repository.curriculum.CurriculumRepository;
import project.homelearn.repository.curriculum.LectureRepository;
import project.homelearn.repository.curriculum.SubjectRepository;
import project.homelearn.repository.user.TeacherRepository;
import project.homelearn.service.common.StorageService;

import java.util.List;

import static project.homelearn.config.storage.FolderType.SUBJECT;

/**
 * Author : 정성진
 */
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class TeacherSubjectService {

    private final StorageService storageService;
    private final TeacherRepository teacherRepository;
    private final SubjectRepository subjectRepository;
    private final LectureRepository lectureRepository;
    private final CurriculumRepository curriculumRepository;
    private final SubjectBoardRepository subjectBoardRepository;
    private final QuestionBoardRepository questionBoardRepository;

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

    public SubjectBasicDto getSubjectBasic(Long subjectId) {
        return subjectRepository.findSubjectBasic(subjectId);
    }

    public List<SubjectBoardTop5Dto> getSubjectBoardTop5(Long subjectId) {
        return subjectBoardRepository.findSubjectBoardTop5(subjectId);
    }

    // 글 등록
    public void writeBoard(Long subjectId, SubjectBoardWriteDto boardDto, String username) {
        Subject subject = subjectRepository.findById(subjectId).orElseThrow();
        SubjectBoard board = new SubjectBoard();
        board.setTitle(boardDto.getTitle());
        board.setContent(boardDto.getContent());
        board.setSubject(subject);

        MultipartFile file = boardDto.getFile();
        if (file != null) {
            Teacher teacher = teacherRepository.findByUsername(username);
            String folderPath = storageService.getFolderPath(teacher, SUBJECT);
            FileDto fileDto = storageService.uploadFile(file, folderPath);

            board.setFilePath(fileDto.getFilePath());
            board.setStoreFileName(fileDto.getUploadFileName());
            board.setUploadFileName(fileDto.getOriginalFileName());
        }

        subjectBoardRepository.save(board);
    }

    // 글 수정
    public boolean modifySubjectBoard(Long subjectId, Long boardId, SubjectBoardWriteDto boardDto, String username) {
        Subject subject = subjectRepository.findSubjectAndCurriculum(subjectId);
        Curriculum curriculum = subject.getCurriculum();

        Teacher teacher = teacherRepository.findByCurriculum(curriculum);
        if (!teacher.getUsername().equals(username)) {
            return false;
        }

        // 수정 로직
        SubjectBoard board = subjectBoardRepository.findById(boardId).orElseThrow();
        board.setTitle(boardDto.getTitle());
        board.setContent(boardDto.getContent());

        MultipartFile file = boardDto.getFile();
        String existFile = board.getFilePath();
        if (file != null) {

            // 파일 수정 + 기존 파일 O
            if (!existFile.isEmpty()) {
                // 기존 파일 삭제
                storageService.deleteFile(existFile);
            }

            // 새로운 파일 등록
            String folderPath = storageService.getFolderPath(teacher, SUBJECT);
            FileDto fileDto = storageService.uploadFile(file, folderPath);
            board.setFilePath(fileDto.getFilePath());
            board.setUploadFileName(fileDto.getOriginalFileName());
            board.setStoreFileName(fileDto.getUploadFileName());
        }
        return true;
    }

    // 글 삭제
    public boolean deleteSubjectBoard(Long subjectId, Long boardId, String username) {
        Subject subject = subjectRepository.findSubjectAndCurriculum(subjectId);
        Curriculum curriculum = subject.getCurriculum();

        Teacher teacher = teacherRepository.findByCurriculum(curriculum);
        if (!teacher.getUsername().equals(username)) {
            return false;
        }

        subjectBoardRepository.deleteById(boardId);
        return true;
    }

    public List<QuestionTop5Dto> getQuestionTop5(Long subjectId) {
        return questionBoardRepository.findQuestionTop5BySubjectId(subjectId);
    }

    public Page<LectureListDto> getSubjectLecturePage(Long subjectId, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return lectureRepository.findSubjectLecturePage(subjectId, pageRequest);
    }

    public Page<SubjectBoardListDto> getSubjectBoardPage(Long subjectId, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        String teacherName = teacherRepository.findTeacherNameBySubjectId(subjectId);
        return lectureRepository.findSubjectBoardPage(subjectId, pageRequest, teacherName);
    }

    public SubjectBoardViewDto getSubjectBoard(Long boardId) {
        return subjectBoardRepository.findSubjectBoard(boardId);
    }

    public List<SubjectSelectListDto> getSubjectSelectList(String username) {
        Curriculum curriculum = curriculumRepository.findCurriculumByUsername(username);
        return subjectRepository.findSubjectSelectList(curriculum);
    }
}