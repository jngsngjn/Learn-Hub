package project.homelearn.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import project.homelearn.entity.student.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

}