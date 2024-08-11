package project.homelearn.repository.badge.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import project.homelearn.dto.student.badge.BadgeViewDto;
import project.homelearn.entity.curriculum.Curriculum;
import project.homelearn.entity.student.Student;
import project.homelearn.entity.student.badge.Badge;
import project.homelearn.entity.student.badge.StudentBadge;
import project.homelearn.repository.badge.StudentBadgeRepository;
import project.homelearn.repository.curriculum.CurriculumRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static project.homelearn.entity.student.badge.QBadge.badge;
import static project.homelearn.entity.student.badge.QStudentBadge.studentBadge;

@RequiredArgsConstructor
public class BadgeRepositoryImpl implements BadgeRepositoryCustom {

    private final JPAQueryFactory queryFactory;
    private final CurriculumRepository curriculumRepository;
    private final StudentBadgeRepository studentBadgeRepository;

    @Override
    public List<BadgeViewDto> findAllBadges(Student student) {
        Curriculum curriculum = curriculumRepository.findCurriculumByUser(student);
        List<Badge> allBadges = queryFactory.selectFrom(badge).fetch();

        return allBadges.stream().map(badge -> {
            BadgeViewDto dto = new BadgeViewDto();
            dto.setBadgeId(badge.getId());
            dto.setName("???");
            dto.setDescription(badge.getDescription());
            dto.setImagePath("");
            dto.setObtainDate(null);
            dto.setObtainCount(0L);

            // 학생이 해당 배지를 획득했는지 확인
            if (studentBadgeRepository.existsByUserAndBadge(student, badge)) {
                StudentBadge studentBadge = studentBadgeRepository.findByUserAndBadge(student, badge);
                dto.setName(badge.getName());
                dto.setDescription(badge.getDescription());
                dto.setImagePath(badge.getImagePath());
                dto.setObtainDate(studentBadge.getGetDate());

                Long earnCount = findBadgeEarnCountByCurriculum(badge, curriculum);
                dto.setObtainCount(earnCount);
            }

            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public List<BadgeViewDto> findEarnBadges(Student student) {
        List<StudentBadge> studentBadges = studentBadgeRepository.findByUser(student);

        return studentBadges.stream().map(studentBadge -> {
            Badge badge = studentBadge.getBadge();
            BadgeViewDto dto = new BadgeViewDto();
            dto.setBadgeId(badge.getId());
            dto.setName(badge.getName());
            dto.setDescription(badge.getDescription());
            dto.setImagePath(badge.getImagePath());
            dto.setObtainDate(studentBadge.getGetDate());

            Long earnCount = findBadgeEarnCountByCurriculum(badge, studentBadge.getCurriculum());
            dto.setObtainCount(earnCount);

            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public List<BadgeViewDto> findNoEarnBadges(Student student) {
        List<Badge> allBadges = queryFactory.selectFrom(badge).fetch();

        List<StudentBadge> earnedBadges = studentBadgeRepository.findByUser(student);

        Set<Long> earnedBadgeIds = earnedBadges.stream()
                .map(studentBadge -> studentBadge.getBadge().getId())
                .collect(Collectors.toSet());

        List<Badge> noEarnBadges = allBadges.stream()
                .filter(badge -> !earnedBadgeIds.contains(badge.getId()))
                .collect(Collectors.toList());

        return noEarnBadges.stream().map(badge -> {
            BadgeViewDto dto = new BadgeViewDto();
            dto.setBadgeId(badge.getId());
            dto.setName("???");
            dto.setDescription(badge.getDescription());
            dto.setImagePath("");
            dto.setObtainDate(null);
            dto.setObtainCount(0L);
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public Long findBadgeEarnCountByCurriculum(Badge badge, Curriculum curriculum) {
        return queryFactory
                .select(studentBadge.count())
                .from(studentBadge)
                .where(studentBadge.badge.eq(badge), studentBadge.curriculum.eq(curriculum))
                .fetchOne();
    }
}
