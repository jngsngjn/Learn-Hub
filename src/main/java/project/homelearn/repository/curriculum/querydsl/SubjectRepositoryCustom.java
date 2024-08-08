package project.homelearn.repository.curriculum.querydsl;

import project.homelearn.dto.common.SubjectIdAndNameDto;
import project.homelearn.entity.curriculum.Curriculum;

import java.util.List;

public interface SubjectRepositoryCustom {

    List<SubjectIdAndNameDto> findSubjectIdsAndNames(Curriculum curriculum);
}