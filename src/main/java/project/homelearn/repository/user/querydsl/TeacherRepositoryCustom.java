package project.homelearn.repository.user.querydsl;

import project.homelearn.dto.manager.manage.curriculum.CurriculumTeacherDto;
import project.homelearn.dto.manager.manage.curriculum.CurriculumWithoutTeacherDto;
import project.homelearn.dto.manager.manage.teacher.SpecificTeacherDto;

public interface TeacherRepositoryCustom {

    CurriculumTeacherDto findByCurriculumId(Long curriculumId);

    SpecificTeacherDto findSpecificTeacher(Long teacherId);

    CurriculumWithoutTeacherDto findCurriculumWithoutTeacher();
}