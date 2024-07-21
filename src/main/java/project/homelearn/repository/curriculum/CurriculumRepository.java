package project.homelearn.repository.curriculum;

import org.springframework.data.jpa.repository.JpaRepository;
import project.homelearn.entity.curriculum.Curriculum;

public interface CurriculumRepository extends JpaRepository<Curriculum, Long> {
}
