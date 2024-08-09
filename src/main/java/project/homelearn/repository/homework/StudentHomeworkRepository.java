package project.homelearn.repository.homework;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project.homelearn.entity.homework.Homework;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project.homelearn.entity.homework.StudentHomework;
import project.homelearn.entity.student.Student;

public interface StudentHomeworkRepository extends JpaRepository<StudentHomework, Long> {

    @Query("SELECT COUNT(sh) > 0 FROM StudentHomework sh WHERE sh.user =:student AND sh.homework =:homework")
    boolean existsByStudentAndHomework(@Param("student") Student student, @Param("homework") Homework homework);

    @Query("select sh from StudentHomework sh where sh.homework.id =:homeworkId")
    StudentHomework findByHomeworkId(@Param("homeworkId") Long homeworkId);
}