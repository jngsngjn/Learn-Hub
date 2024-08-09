package project.homelearn.repository.badge.querydsl;

import project.homelearn.dto.student.badge.BadgeViewDto;
import project.homelearn.dto.student.dashboard.ViewMyBadgeDto;
import project.homelearn.entity.student.Student;

import java.util.List;

public interface StudentBadgeRepositoryCustom {

    List<ViewMyBadgeDto> findStudentBadgeTop4(String username);

    List<BadgeViewDto> findAllBadges(Student student);

    List<BadgeViewDto> findEarnBadges(Student student);

    List<BadgeViewDto> findNoEarnBadges(Student student);
}