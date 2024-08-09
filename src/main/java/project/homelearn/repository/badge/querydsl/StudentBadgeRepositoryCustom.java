package project.homelearn.repository.badge.querydsl;

import project.homelearn.dto.student.dashboard.ViewMyBadgeDto;

import java.util.List;

public interface StudentBadgeRepositoryCustom {

    List<ViewMyBadgeDto> findStudentBadgeTop4(String username);
}