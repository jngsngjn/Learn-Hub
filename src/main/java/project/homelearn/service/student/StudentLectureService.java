package project.homelearn.service.student;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.homelearn.dto.student.dashboard.ViewLectureDto;
import project.homelearn.dto.student.dashboard.ViewRecentStudyLectureDto;
import project.homelearn.repository.curriculum.LectureRepository;
import project.homelearn.repository.curriculum.StudentLectureRepository;

import java.util.Optional;

@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class StudentLectureService {

    private final LectureRepository lectureRepository;
    private final StudentLectureRepository myLectureRepository;

    // 최근 영상 or 최근 영상 중 안본 영상
    public Optional<ViewLectureDto> getLecture(String username){
        return lectureRepository.findLatestUnwatchedOrRecentLecture(username);
    }

    // 최근 내가 학습한 강의 상태 보기
    public Optional<ViewRecentStudyLectureDto> getRecentLecture(String username){
        return myLectureRepository.findRecentStudyLectureByUsername(username);
    }

}
