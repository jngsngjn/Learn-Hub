package project.homelearn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.homelearn.entity.teacher.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

}