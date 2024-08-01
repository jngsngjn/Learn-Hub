package project.homelearn.repository.homework;

import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import project.homelearn.entity.homework.Homework;

public interface HomeworkRepository extends JpaRepository<Homework, Long> {

    @Query("select h from Homework h join fetch h.curriculum where h.id =:homeworkId")
    Homework findHomeworkAndCurriculum(@Param("homeworkId") Long homeworkId);
}