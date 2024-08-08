package project.homelearn.repository.user.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import project.homelearn.dto.manager.enroll.QTeacherIdAndName;
import project.homelearn.dto.manager.enroll.TeacherIdAndName;
import project.homelearn.dto.manager.manage.curriculum.CurriculumTeacherDto;
import project.homelearn.dto.manager.manage.curriculum.QCurriculumTeacherDto;
import project.homelearn.dto.manager.manage.teacher.QSpecificTeacherDto;
import project.homelearn.dto.manager.manage.teacher.SpecificTeacherDto;

import java.util.List;

import static project.homelearn.entity.curriculum.QCurriculum.curriculum;
import static project.homelearn.entity.teacher.QTeacher.teacher;

@RequiredArgsConstructor
public class TeacherRepositoryImpl implements TeacherRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public CurriculumTeacherDto findByCurriculumId(Long curriculumId) {
        return queryFactory
                .select(new QCurriculumTeacherDto(
                        teacher.name,
                        teacher.email,
                        teacher.phone
                ))
                .from(teacher)
                .join(teacher.curriculum, curriculum)
                .fetchOne();
    }

    @Override
    public SpecificTeacherDto findSpecificTeacher(Long teacherId) {
        return queryFactory
                .select(new QSpecificTeacherDto(teacher.id, teacher.name, teacher.email, teacher.phone, curriculum.id, curriculum.type, curriculum.th))
                .from(teacher)
                .leftJoin(teacher.curriculum, curriculum)
                .where(teacher.id.eq(teacherId))
                .fetchOne();
    }

    @Override
    public List<TeacherIdAndName> findTeacherIdsAndNames() {
        return queryFactory
                .select(new QTeacherIdAndName(teacher.id, teacher.name))
                .from(teacher)
                .where(teacher.curriculum.isNull())
                .fetch();
    }
}