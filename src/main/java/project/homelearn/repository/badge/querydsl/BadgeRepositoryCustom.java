package project.homelearn.repository.badge.querydsl;

import project.homelearn.dto.student.badge.BadgeViewDto;
import project.homelearn.entity.curriculum.Curriculum;
import project.homelearn.entity.student.Student;
import project.homelearn.entity.student.badge.Badge;

import java.util.List;

public interface BadgeRepositoryCustom {

    List<BadgeViewDto> findAllBadges(Student student);

    List<BadgeViewDto> findEarnBadges(Student student);

    List<BadgeViewDto> findNoEarnBadges(Student student);

    Long findBadgeEarnCountByCurriculum(Badge badge, Curriculum curriculum);
}