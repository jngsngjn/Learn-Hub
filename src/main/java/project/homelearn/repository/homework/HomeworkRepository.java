package project.homelearn.repository.homework;

import org.springframework.data.jpa.repository.JpaRepository;
import project.homelearn.entity.homework.Homework;

public interface HomeworkRepository extends JpaRepository<Homework, Long> {
}