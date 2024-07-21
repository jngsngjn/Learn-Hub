package project.homelearn.repository.curriculum;

import org.springframework.data.jpa.repository.JpaRepository;
import project.homelearn.entity.curriculum.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
