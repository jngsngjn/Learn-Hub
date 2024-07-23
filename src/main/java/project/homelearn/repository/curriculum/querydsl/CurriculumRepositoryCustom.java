package project.homelearn.repository.curriculum.querydsl;

import project.homelearn.entity.curriculum.CurriculumType;

public interface CurriculumRepositoryCustom {

    Long findCountByType(CurriculumType type);
}