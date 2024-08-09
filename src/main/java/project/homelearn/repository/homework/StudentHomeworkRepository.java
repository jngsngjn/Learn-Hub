package project.homelearn.repository.homework;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project.homelearn.entity.homework.StudentHomework;

public interface StudentHomeworkRepository extends JpaRepository<StudentHomework, Long> {

    @Query("select sh from StudentHomework sh where sh.homework.id =:homeworkId")
    StudentHomework findByHomeworkId(@Param("homeworkId") Long homeworkId);
}