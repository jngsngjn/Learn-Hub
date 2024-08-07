package project.homelearn.repository.user;

import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import project.homelearn.dto.manager.manage.curriculum.CurriculumBasicDto;
import project.homelearn.dto.manager.manage.student.SpecificStudentDto;
import project.homelearn.entity.curriculum.Curriculum;
import project.homelearn.entity.student.Student;
import project.homelearn.repository.user.querydsl.StudentRepositoryCustom;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long>, StudentRepositoryCustom {

    Student findByUsername(String username);

    // 필터링 x : 전체 학생 조회
    Page<Student> findAllByOrderByCreatedDateDesc(Pageable pageable);

    // 필터링 o : 교육과정명 기준 학생 조회
    @Query("SELECT s FROM Student s JOIN FETCH s.curriculum c WHERE c.name =:curriculumName")
    Page<Student> findByCurriculumName(Pageable pageable, @Param("curriculumName") String curriculumName);

    // 필터링 o : 기수 + 교육과정명 기준 학생 조회
    @Query("SELECT s FROM Student s JOIN FETCH s.curriculum c WHERE c.th = :curriculumTh AND c.name = :curriculumName")
    Page<Student> findByCurriculumThAndCurriculumName(Pageable pageable, @Param("curriculumName") String curriculumName, @Param("curriculumTh") Long curriculumTh);

    // 설문 시작 후 해당 커리큘럼의 모든 학생의 설문 완료 상태를 false로 변경
    @Modifying
    @Transactional
    @Query("update Student s set s.surveyCompleted = false where s.curriculum.id =:curriculumId")
    void updateSurveyCompletedFalse(@Param("curriculumId") Long curriculumId);

    @Query("select new project.homelearn.dto.manager.manage.student.SpecificStudentDto(s.id, s.name, s.email, s.phone, s.gender) from Student s where s.id =:studentId")
    SpecificStudentDto findSpecificStudentDto(@Param("studentId") Long studentId);

    @Query("select new project.homelearn.dto.manager.manage.curriculum.CurriculumBasicDto(c.name, c.th, c.startDate, c.endDate) " +
            "from Student s join fetch Curriculum c on s.curriculum.id = c.id " +
            "where s.id =:studentId")
    CurriculumBasicDto findStudentCurriculum(@Param("studentId") Long studentId);

    @Query("SELECT s FROM Student s WHERE s.id NOT IN (SELECT lh.user.id FROM LoginHistory lh)")
    List<Student> findAbsentStudents();

    // 학생이 속해있는 커리큘럼의 총 교육과정 일수
    @Query("SELECT c FROM Student s JOIN s.curriculum c WHERE s.id = :studentId")
    Curriculum findCurriculumByStudentId(@Param("studentId") Long studentId);

    // 커리큘럼 넣으면 커리큘럼의 총 학생 수 반환
    @Query("select count(s) from Student s where s.curriculum =:curriculum")
    Integer findStudentCountByCurriculum(@Param("curriculum") Curriculum curriculum);

    @Query("select s.name from Student s where s.id =:studentId")
    String findStudentName(@Param("studentId") Long studentId);

    @Query("select s.id from Student s where s.curriculum =:curriculum")
    List<Long> findAllStudentIds(@Param("curriculum") Curriculum curriculum);

    List<Student> findAllByCurriculum(Curriculum curriculum);
}