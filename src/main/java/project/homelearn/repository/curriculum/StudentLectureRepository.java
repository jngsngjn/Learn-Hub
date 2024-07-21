package project.homelearn.repository.curriculum;

import org.springframework.data.jpa.repository.JpaRepository;
import project.homelearn.entity.curriculum.StudentLecture;

public interface StudentLectureRepository extends JpaRepository<StudentLecture, Long> {
}
