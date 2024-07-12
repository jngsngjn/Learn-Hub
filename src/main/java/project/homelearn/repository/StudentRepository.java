package project.homelearn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.homelearn.entity.student.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

}