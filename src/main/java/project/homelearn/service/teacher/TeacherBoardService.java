package project.homelearn.service.teacher;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import project.homelearn.config.storage.StorageConstants;
import project.homelearn.dto.common.FileDto;
import project.homelearn.dto.teacher.board.NoticeBoardDto;
import project.homelearn.dto.teacher.board.NoticeReadDto;
import project.homelearn.dto.teacher.board.NoticeUpdateDto;
import project.homelearn.dto.teacher.board.TeacherBoardDto;
import project.homelearn.dto.teacher.dashboard.ManagerBoardDto;
import project.homelearn.entity.curriculum.Curriculum;
import project.homelearn.entity.teacher.TeacherBoard;
import project.homelearn.repository.board.TeacherBoardRepository;
import project.homelearn.repository.curriculum.CurriculumRepository;
import project.homelearn.service.common.StorageService;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class TeacherBoardService {

    private final CurriculumRepository curriculumRepository;
    private final TeacherBoardRepository teacherBoardRepository;
    private final StorageService storageService;

    // 생성
    public boolean addTeacherBoard(String username, NoticeBoardDto addBoardDto) {
        try {
            Curriculum curriculum = curriculumRepository.findCurriculumByUsername(username);

            TeacherBoard board = new TeacherBoard();
            board.setCurriculum(curriculum);
            board.setEmergency(addBoardDto.getIsEmergency());
            board.setTitle(addBoardDto.getTitle());
            board.setContent(addBoardDto.getContent());

            MultipartFile file = addBoardDto.getFile();
            if (file != null && !file.isEmpty()) {
                FileDto fileDto = storageService.uploadFile(file, StorageConstants.ANNOUNCEMENT_STORAGE);
                board.setUploadFileName(fileDto.getOriginalFileName());
                board.setStoreFileName(fileDto.getUploadFileName());
                board.setFilePath(fileDto.getFilePath());
            }

            teacherBoardRepository.save(board);
            return true;
        } catch (Exception e) {
            log.error("Error add TeacherBoard", e);
            return false;
        }
    }

    // 조회
    public Page<NoticeReadDto> getNoticeList(Curriculum curriculum, Pageable pageable) {
        Page<TeacherBoard> noticeListPage = teacherBoardRepository.findTeacherBoardsBy(curriculum, pageable);
        return noticeListPage.map(this::convertToListWithFileDto);
    }

    // 조회를 위한 DTO변환
    private NoticeReadDto convertToListWithFileDto (TeacherBoard teacherBoard) {
        return new NoticeReadDto(
                teacherBoard.getId(),
                teacherBoard.getTitle(),
                teacherBoard.getContent(),
                teacherBoard.getCreatedDate(),
                teacherBoard.isEmergency(),
                teacherBoard.getFilePath(),
                teacherBoard.getUploadFileName()
        );
    }

    // 수정
    public boolean updateTeacherBoard(Long boardId, NoticeUpdateDto updateBoardDto) {
        try {
            TeacherBoard board = teacherBoardRepository.findById(boardId)
                    .orElseThrow(() -> new RuntimeException("Teacher Board Not Found"));
            board.setTitle(updateBoardDto.getTitle());
            board.setContent(updateBoardDto.getContent());
            board.setEmergency(updateBoardDto.getIsEmergency());

            MultipartFile file = updateBoardDto.getFile();
            String previousFilePath = board.getFilePath();

            // 파일 삭제 조건
            if (updateBoardDto.isUseDefaultFile() || (file != null && !file.isEmpty())) {
                if (previousFilePath != null) {
                    storageService.deleteFile(previousFilePath);
                    board.setUploadFileName(null);
                    board.setStoreFileName(null);
                    board.setFilePath(null);
                }
            }

            // 새 파일 업로드 조건
            if (file != null && !file.isEmpty()) {
                FileDto fileDto = storageService.uploadFile(file, StorageConstants.ANNOUNCEMENT_STORAGE);
                board.setUploadFileName(fileDto.getOriginalFileName());
                board.setStoreFileName(fileDto.getUploadFileName());
                board.setFilePath(fileDto.getFilePath());
            }

            teacherBoardRepository.save(board);
            return true;
        } catch (Exception e) {
            log.error("Error update TeacherBoard", e);
            return false;
        }
    }

    // 삭제
    public boolean deleteTeacherBoard(List<Long> boardIds) {
        try {
            List<TeacherBoard> boards = teacherBoardRepository.findAllById(boardIds);
            for (TeacherBoard board : boards) {
                String file = board.getFilePath();
                if(file != null) {
                    storageService.deleteFile(file);
                }
            }

            teacherBoardRepository.deleteAllById(boardIds);
            return true;
        } catch (Exception e) {
            log.error("Error delete TeacherBoard", e);
            return false;
        }
    }

    // 최근 4개 공지사항
    public List<TeacherBoardDto> viewTeacherBoardRecent() {
        return teacherBoardRepository.findTeacherBoardRecent4();
    }
}