package project.homelearn.repository.badge;

import org.springframework.data.jpa.repository.JpaRepository;
import project.homelearn.entity.student.badge.Badge;
import project.homelearn.repository.badge.querydsl.BadgeRepositoryCustom;

public interface BadgeRepository extends JpaRepository<Badge, Long>, BadgeRepositoryCustom {

    Badge findByName(String name);
}