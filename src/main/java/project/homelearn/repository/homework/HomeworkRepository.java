package project.homelearn.repository.homework;

import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import project.homelearn.entity.curriculum.Curriculum;
import project.homelearn.entity.homework.Homework;
import project.homelearn.repository.homework.querydsl.HomeworkRepositoryCustom;

import java.time.LocalDateTime;

public interface HomeworkRepository extends JpaRepository<Homework, Long>, HomeworkRepositoryCustom {

    @Query("select h from Homework h join fetch h.curriculum where h.id =:homeworkId")
    Homework findHomeworkAndCurriculum(@Param("homeworkId") Long homeworkId);

    // 마감 기준 오름 차순
    @Query("select h from Homework h where h.curriculum = :curriculum and h.deadline > :now order by h.createdDate desc")
    Page<Homework> findProceedingHomework(@Param("curriculum") Curriculum curriculum, @Param("now") LocalDateTime now, Pageable pageable);

    // 마감된 과제들 날짜순으로
    @Query("select h from Homework h where h.curriculum = :curriculum and h.deadline < :now order by h.createdDate desc")
    Page<Homework> findClosedHomework(@Param("curriculum") Curriculum curriculum, @Param("now") LocalDateTime now, Pageable pageable);
}