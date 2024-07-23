package project.homelearn.repository.user;

import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import project.homelearn.entity.student.Student;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    //필터링 x : 전체 학생 조회
    Page<Student> findAllByOrderByCreatedDateDesc(Pageable pageable);

    //필터링 o : 교육과정명 기준 학생 조회
    @Query("SELECT s FROM Student s JOIN FETCH s.curriculum c WHERE c.name = :curriculumName")
    Page<Student> findByCurriculumName(Pageable pageable, @Param("curriculumName") String curriculumName);

    //필터링 o : 기수 + 교육과정명 기준 학생 조회
    @Query("SELECT s FROM Student s JOIN FETCH s.curriculum c WHERE c.th = :curriculumTh AND c.name = :curriculumName")
    Page<Student> findByCurriculumThAndCurriculumName(Pageable pageable, @Param("curriculumName") String curriculumName, @Param("curriculumTh")Long curriculumTh);
}