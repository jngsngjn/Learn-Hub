package project.homelearn.repository.curriculum;

import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import project.homelearn.dto.teacher.subject.SubjectBasicDto;
import project.homelearn.entity.curriculum.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Long> {

    @Query("select s from Subject s join fetch s.curriculum where s.id =:subjectId")
    Subject findSubjectAndCurriculum(@Param("subjectId") Long subjectId);

    @Query("select new project.homelearn.dto.teacher.subject.SubjectBasicDto(s.id, s.name, s.description, s.imagePath) from Subject s where s.id =:subjectId")
    SubjectBasicDto findSubjectBasic(@Param("subjectId") Long subjectId);
}