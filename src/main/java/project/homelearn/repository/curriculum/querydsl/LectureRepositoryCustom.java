package project.homelearn.repository.curriculum.querydsl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import project.homelearn.dto.teacher.lecture.LectureListDto;

public interface LectureRepositoryCustom {

    Page<LectureListDto> findSubjectLecturePage(Long subjectId, Pageable pageable);
}