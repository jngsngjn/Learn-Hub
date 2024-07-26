package project.homelearn.repository.curriculum;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project.homelearn.dto.manager.manage.curriculum.CurriculumBasicDto;
import project.homelearn.entity.curriculum.Curriculum;
import project.homelearn.entity.curriculum.CurriculumType;
import project.homelearn.repository.curriculum.querydsl.CurriculumRepositoryCustom;

import java.util.List;

public interface CurriculumRepository extends JpaRepository<Curriculum, Long>, CurriculumRepositoryCustom {

    Curriculum findByFullName(String fullName);

    @Query("select new project.homelearn.dto.manager.manage.curriculum.CurriculumBasicDto(c.name, c.th, c.startDate, c.endDate) from Curriculum c where c.id =:id")
    CurriculumBasicDto findCurriculumBasic(@Param("id") Long id);

    @Query("select c from Curriculum c where c.type = :type order by c.th ASC ")
    List<Curriculum> findByCurriculumType(@Param("type")String type);

}