package project.homelearn.repository.curriculum;

import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import project.homelearn.entity.curriculum.Lecture;

public interface LectureRepository extends JpaRepository<Lecture, Long> {

    @Query("select l from Lecture l join fetch l.curriculum c where l.id =:lectureId")
    Lecture findLectureAndCurriculum(@Param("lectureId") Long lectureId);
}