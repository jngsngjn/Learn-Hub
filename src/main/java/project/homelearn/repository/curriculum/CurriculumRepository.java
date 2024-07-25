package project.homelearn.repository.curriculum;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project.homelearn.dto.manager.manage.curriculum.CurriculumBasicDto;
import project.homelearn.entity.curriculum.Curriculum;
import project.homelearn.repository.curriculum.querydsl.CurriculumRepositoryCustom;

public interface CurriculumRepository extends JpaRepository<Curriculum, Long>, CurriculumRepositoryCustom {

    Curriculum findByFullName(String fullName);

    @Query("select new project.homelearn.dto.manager.manage.curriculum.CurriculumBasicDto(c.name, c.th, c.startDate, c.endDate) from Curriculum c where c.id =:id")
    CurriculumBasicDto findCurriculumBasic(@Param("id") Long id);
}