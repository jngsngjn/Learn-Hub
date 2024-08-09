package project.homelearn.repository.curriculum.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import project.homelearn.dto.manager.calendar.CurriculumNameAndColor;
import project.homelearn.dto.manager.calendar.QCurriculumNameAndColor;
import project.homelearn.dto.manager.manage.curriculum.CurriculumIdAndThDto;
import project.homelearn.dto.manager.manage.curriculum.CurriculumTypeAndTh;
import project.homelearn.dto.manager.manage.curriculum.CurriculumWithoutTeacherDto;
import project.homelearn.dto.manager.manage.curriculum.QCurriculumIdAndThDto;
import project.homelearn.entity.curriculum.Curriculum;
import project.homelearn.entity.curriculum.CurriculumType;
import project.homelearn.entity.user.QUser;
import project.homelearn.entity.user.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static project.homelearn.entity.curriculum.CurriculumType.AWS;
import static project.homelearn.entity.curriculum.CurriculumType.NCP;
import static project.homelearn.entity.curriculum.QCurriculum.curriculum;
import static project.homelearn.entity.user.QUser.user;

@RequiredArgsConstructor
public class CurriculumRepositoryImpl implements CurriculumRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Long findCountByType(CurriculumType type) {
        return queryFactory
                .select(curriculum.count())
                .from(curriculum)
                .where(
                        curriculum.type.eq(type),
                        curriculum.endDate.after(LocalDate.now())
                )
                .fetchOne();
    }

    @Override
    public List<CurriculumWithoutTeacherDto> findCurriculumWithoutTeacher() {

        // NCP 교육과정
        List<CurriculumIdAndThDto> ncp = queryFactory
                .select(new QCurriculumIdAndThDto(curriculum.id, curriculum.th))
                .from(curriculum)
                .leftJoin(curriculum.users, user)
                .where(user.isNull(), curriculum.type.eq(NCP))
                .fetch();

        // AWS 교육과정
        List<CurriculumIdAndThDto> aws = queryFactory
                .select(new QCurriculumIdAndThDto(curriculum.id, curriculum.th))
                .from(curriculum)
                .leftJoin(curriculum.users, user)
                .where(user.isNull(), curriculum.type.eq(AWS))
                .fetch();

        // 결과를 DTO로 변환하여 반환
        List<CurriculumWithoutTeacherDto> curriculumWithoutTeacherDtos = new ArrayList<>();
        curriculumWithoutTeacherDtos.add(new CurriculumWithoutTeacherDto(NCP, ncp));
        curriculumWithoutTeacherDtos.add(new CurriculumWithoutTeacherDto(AWS, aws));

        return curriculumWithoutTeacherDtos;
    }

    @Override
    public List<CurriculumTypeAndTh> findCurriculumTypeAndTh() {
        CurriculumTypeAndTh ncp = new CurriculumTypeAndTh();
        ncp.setType(NCP);

        List<Long> ncpThs = queryFactory
                .select(curriculum.th)
                .from(curriculum)
                .where(curriculum.type.eq(NCP))
                .fetch();
        ncp.setTh(ncpThs);

        CurriculumTypeAndTh aws = new CurriculumTypeAndTh();
        aws.setType(AWS);

        List<Long> awsThs = queryFactory
                .select(curriculum.th)
                .from(curriculum)
                .where(curriculum.type.eq(AWS))
                .fetch();
        aws.setTh(awsThs);

        List<CurriculumTypeAndTh> result = new ArrayList<>();
        result.add(ncp);
        result.add(aws);

        return result;
    }

    @Override
    public Curriculum findCurriculumByUsername(String username) {
        return queryFactory
                .selectFrom(curriculum)
                .join(curriculum.users, user)
                .where(user.username.eq(username))
                .fetchOne();
    }

    @Override
    public Curriculum findCurriculumByUser(User user) {
        return queryFactory
                .selectFrom(curriculum)
                .join(curriculum.users, QUser.user)
                .where(QUser.user.eq(user))
                .fetchOne();
    }

    @Override
    public List<CurriculumNameAndColor> findCurriculumNameAndColor() {
        return queryFactory
                .select(new QCurriculumNameAndColor(curriculum.fullName, curriculum.color))
                .from(curriculum)
                .orderBy(curriculum.createdDate.asc())
                .fetch();
    }
}