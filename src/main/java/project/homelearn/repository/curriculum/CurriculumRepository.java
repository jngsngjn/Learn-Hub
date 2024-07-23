package project.homelearn.repository.curriculum;

import org.springframework.data.jpa.repository.JpaRepository;
import project.homelearn.entity.curriculum.Curriculum;
import project.homelearn.repository.curriculum.querydsl.CurriculumRepositoryCustom;

public interface CurriculumRepository extends JpaRepository<Curriculum, Long>, CurriculumRepositoryCustom {
}