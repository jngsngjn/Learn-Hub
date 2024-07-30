package project.homelearn.repository.user;

import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import project.homelearn.entity.teacher.Teacher;
import project.homelearn.repository.user.querydsl.TeacherRepositoryCustom;

public interface TeacherRepository extends JpaRepository<Teacher, Long>, TeacherRepositoryCustom {

    //필터링 x : 전체 강사 조회
    Page<Teacher> findAllByOrderByCreatedDateDesc(Pageable pageable);

    //필터링 o : 교육과정명 기준 강사 조회
    @Query("SELECT t FROM Teacher t JOIN FETCH t.curriculum c WHERE c.name = :curriculumName")
    Page<Teacher> findByCurriculumName(Pageable pageable, @Param("curriculumName") String curriculumName);

    //필터링 o : 배정안된 강사만 (혹시 몰라서 만들었는데)
    @Query("SELECT t FROM Teacher t JOIN FETCH t.curriculum c WHERE c.id = NULL")
    Page<Teacher> findByCurriculumIdIsNull(Pageable pageable);

    @Query("select t From Teacher t join fetch t.curriculum c where t.username =:username")
    Teacher findByUsernameFetch(@Param("username") String username);
}