package project.homelearn.repository.user.querydsl;

import project.homelearn.dto.manager.manage.curriculum.CurriculumTeacherDto;

public interface TeacherRepositoryCustom {

    CurriculumTeacherDto findByCurriculumId(Long curriculumId);
}