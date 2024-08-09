package project.homelearn.repository.badge.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import project.homelearn.dto.student.badge.BadgeViewDto;
import project.homelearn.dto.student.dashboard.QViewMyBadgeDto;
import project.homelearn.dto.student.dashboard.ViewMyBadgeDto;
import project.homelearn.entity.student.Student;

import java.util.List;

import static project.homelearn.entity.student.badge.QBadge.badge;
import static project.homelearn.entity.student.badge.QStudentBadge.studentBadge;
import static project.homelearn.entity.user.QUser.user;

@RequiredArgsConstructor
public class StudentBadgeRepositoryImpl implements StudentBadgeRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public List<ViewMyBadgeDto> findStudentBadgeTop4(String username) {
        return queryFactory
                .select(new QViewMyBadgeDto(
                        studentBadge.id,
                        badge.name,
                        badge.imageName,
                        badge.imagePath,
                        studentBadge.getDate
                ))
                .from(studentBadge)
                .join(studentBadge.badge, badge)
                .join(studentBadge.user, user)
                .where(user.username.eq(username))
                .orderBy(studentBadge.getDate.desc())
                .limit(4)
                .fetch();
    }

    @Override
    public List<BadgeViewDto> findAllBadges(Student student) {
        return List.of();
    }

    @Override
    public List<BadgeViewDto> findEarnBadges(Student student) {


        return List.of();
    }

    @Override
    public List<BadgeViewDto> findNoEarnBadges(Student student) {
        return List.of();
    }
}