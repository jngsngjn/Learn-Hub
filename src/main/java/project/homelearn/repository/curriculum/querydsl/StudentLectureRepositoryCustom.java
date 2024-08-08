package project.homelearn.repository.curriculum.querydsl;

import project.homelearn.dto.student.dashboard.ViewRecentStudyLectureDto;

import java.util.Optional;

public interface StudentLectureRepositoryCustom {
    Optional<ViewRecentStudyLectureDto> findRecentStudyLectureByUsername(String username);
}
