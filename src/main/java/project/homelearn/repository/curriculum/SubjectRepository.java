package project.homelearn.repository.curriculum;

import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import project.homelearn.dto.teacher.subject.SubjectBasicDto;
import project.homelearn.dto.teacher.subject.SubjectSelectListDto;
import project.homelearn.entity.curriculum.Curriculum;
import project.homelearn.entity.curriculum.Subject;
import project.homelearn.repository.curriculum.querydsl.SubjectRepositoryCustom;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, Long>, SubjectRepositoryCustom {

    @Query("select s from Subject s join fetch s.curriculum where s.id =:subjectId")
    Subject findSubjectAndCurriculum(@Param("subjectId") Long subjectId);

    @Query("select new project.homelearn.dto.teacher.subject.SubjectBasicDto(s.id, s.name, s.description, s.imagePath) from Subject s where s.id =:subjectId")
    SubjectBasicDto findSubjectBasic(@Param("subjectId") Long subjectId);

    @Query("select new project.homelearn.dto.teacher.subject.SubjectSelectListDto(s.id, s.name) from Subject s where s.curriculum =:curriculum")
    List<SubjectSelectListDto> findSubjectSelectList(Curriculum curriculum);
}