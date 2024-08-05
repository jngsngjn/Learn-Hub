package project.homelearn.repository.curriculum.querydsl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import project.homelearn.dto.teacher.lecture.LectureListDto;
import project.homelearn.dto.teacher.subject.SubjectBoardListDto;

public interface LectureRepositoryCustom {

    Page<LectureListDto> findSubjectLecturePage(Long subjectId, Pageable pageable);

    Page<SubjectBoardListDto> findSubjectBoardPage(Long subjectId, Pageable pageable, String teacherName);
}