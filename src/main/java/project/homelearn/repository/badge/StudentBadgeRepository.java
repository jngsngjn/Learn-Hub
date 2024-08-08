package project.homelearn.repository.badge;

import org.springframework.data.jpa.repository.JpaRepository;
import project.homelearn.entity.student.badge.StudentBadge;
import project.homelearn.repository.badge.querydsl.StudentBadgeRepositoryCustom;

public interface StudentBadgeRepository extends JpaRepository<StudentBadge, Long> , StudentBadgeRepositoryCustom {
}