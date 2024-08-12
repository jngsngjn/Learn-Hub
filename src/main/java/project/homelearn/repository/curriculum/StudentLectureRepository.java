package project.homelearn.repository.curriculum;

import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import project.homelearn.entity.curriculum.StudentLecture;
import project.homelearn.repository.curriculum.querydsl.StudentLectureRepositoryCustom;

public interface StudentLectureRepository extends JpaRepository<StudentLecture, Long> , StudentLectureRepositoryCustom {

    // 특정 강의를 수강 완료한 학생 수 반환
    @Query("select count(sl) from StudentLecture sl where sl.lecture.id =:lectureId and sl.isCompleted = true")
    Integer findCompleteCount(@Param("lectureId") Long lectureId);
}