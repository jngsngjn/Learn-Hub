package project.homelearn.repository.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import project.homelearn.entity.student.Student;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Page<Student> findAllByOrderByCreatedDateDesc(Pageable pageable);

}