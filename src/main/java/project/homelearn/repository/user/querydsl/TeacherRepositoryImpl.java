package project.homelearn.repository.user.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import project.homelearn.dto.manager.manage.curriculum.CurriculumTeacherDto;
import project.homelearn.dto.manager.manage.curriculum.QCurriculumTeacherDto;

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
}