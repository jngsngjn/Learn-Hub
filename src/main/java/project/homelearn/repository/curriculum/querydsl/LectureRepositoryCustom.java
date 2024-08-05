package project.homelearn.repository.curriculum.querydsl;

import project.homelearn.dto.teacher.lecture.LectureListDto;

import java.util.List;

public interface LectureRepositoryCustom {

    List<LectureListDto> findLectureListTop6(Long subjectId);
}