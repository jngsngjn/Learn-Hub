package project.homelearn.repository.curriculum;

import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import project.homelearn.entity.curriculum.Lecture;
import project.homelearn.repository.curriculum.querydsl.LectureRepositoryCustom;

public interface LectureRepository extends JpaRepository<Lecture, Long>, LectureRepositoryCustom {

    @Query("select l from Lecture l join fetch l.curriculum c where l.id =:lectureId")
    Lecture findLectureAndCurriculum(@Param("lectureId") Long lectureId);
}