package project.homelearn.repository.curriculum.querydsl;

import project.homelearn.dto.manager.calendar.CurriculumNameAndColor;
import project.homelearn.dto.manager.manage.curriculum.CurriculumTypeAndTh;
import project.homelearn.dto.manager.manage.curriculum.CurriculumWithoutTeacherDto;
import project.homelearn.entity.curriculum.Curriculum;
import project.homelearn.entity.curriculum.CurriculumType;

import java.util.List;

public interface CurriculumRepositoryCustom {

    Long findCountByType(CurriculumType type);

    List<CurriculumWithoutTeacherDto> findCurriculumWithoutTeacher();

    List<CurriculumTypeAndTh> findCurriculumTypeAndTh();

    Curriculum findCurriculumByTeacher(String username);

    Curriculum findCurriculumByStudent(String username);

    List<CurriculumNameAndColor> findCurriculumNameAndColor();
}