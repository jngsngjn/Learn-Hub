package project.homelearn.repository.homework;

import org.springframework.data.jpa.repository.JpaRepository;
import project.homelearn.entity.homework.StudentHomework;

public interface StudentHomeworkRepository extends JpaRepository<StudentHomework, Long> {
}