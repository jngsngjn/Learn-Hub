package project.homelearn.repository.badge;

import org.springframework.data.jpa.repository.JpaRepository;
import project.homelearn.entity.student.badge.Badge;

public interface BadgeRepository extends JpaRepository<Badge, Long> {

    Badge findByName(String name);
}