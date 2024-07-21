package project.homelearn.repository.curriculum;

import org.springframework.data.jpa.repository.JpaRepository;
import project.homelearn.entity.curriculum.Lecture;

public interface LectureRepository extends JpaRepository<Lecture, Long> {
}
