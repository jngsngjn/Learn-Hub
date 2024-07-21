package project.homelearn.repository.badge;

import org.springframework.data.jpa.repository.JpaRepository;
import project.homelearn.entity.student.badge.StudentBadge;

public interface StudentBadgeRepository extends JpaRepository<StudentBadge, Long> {
}