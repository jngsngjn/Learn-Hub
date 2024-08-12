package project.homelearn.repository.user.querydsl;

import project.homelearn.dto.manager.enroll.TeacherIdAndName;
import project.homelearn.dto.manager.manage.curriculum.CurriculumTeacherDto;
import project.homelearn.dto.manager.manage.teacher.SpecificTeacherDto;
import project.homelearn.entity.teacher.Teacher;

import java.util.List;

public interface TeacherRepositoryCustom {

    CurriculumTeacherDto findByCurriculumId(Long curriculumId);

    SpecificTeacherDto findSpecificTeacher(Long teacherId);

    List<TeacherIdAndName> findTeacherIdsAndNames();

    Teacher findByStudentUsername(String username);
}