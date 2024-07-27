package project.homelearn.repository.inquiry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project.homelearn.entity.inquiry.ManagerInquiry;
import project.homelearn.entity.user.Role;

import java.util.List;

public interface ManagerInquiryRepository extends JpaRepository<ManagerInquiry, Long> {

    // 학생 문의 내역
    // 문의 목록 작성시간 순으로 추출
    @Query("SELECT i FROM ManagerInquiry i JOIN FETCH i.user u WHERE TYPE(u) = Student ORDER BY CASE WHEN i.response IS NULL THEN 0 ELSE 1 END, i.createdDate DESC")
    List<ManagerInquiry> findStudentInquiriesAllDefault();

    // 문의 목록 커리큘럼 이름 기준
    @Query("SELECT i FROM ManagerInquiry i JOIN FETCH i.user u JOIN FETCH u.curriculum c WHERE TYPE(u) = Student AND c.name = :curriculumName ORDER BY CASE WHEN i.response IS NULL THEN 0 ELSE 1 END, i.createdDate DESC")
    List<ManagerInquiry> findStudentInquiriesWithCurriculumName(@Param("curriculumName") String curriculumName);

    // 문의 목록 커리큘럼 이름 + 기수 기준
    @Query("SELECT i FROM ManagerInquiry i JOIN FETCH i.user u JOIN FETCH u.curriculum c WHERE TYPE(u) = Student AND c.name = :curriculumName AND c.th = :curriculumTh ORDER BY CASE WHEN i.response IS NULL THEN 0 ELSE 1 END, i.createdDate DESC")
    List<ManagerInquiry> findStudentInquiriesWithCurriculumNameAndCurriculumTh(@Param("curriculumName") String curriculumName, @Param("curriculumTh") Long curriculumTh);

    // 강사 문의 내역
    // 문의 목록 작성시간 순으로 추출
    @Query("SELECT i FROM ManagerInquiry i JOIN FETCH i.user u WHERE TYPE(u) = Teacher ORDER BY CASE WHEN i.response IS NULL THEN 0 ELSE 1 END, i.createdDate DESC")
    List<ManagerInquiry> findTeacherInquiresAllDefault();

    // 문의 목록 커리큘럼 이름 기준
    @Query("SELECT i FROM ManagerInquiry i JOIN FETCH i.user u JOIN FETCH u.curriculum c WHERE TYPE(u) = Teacher AND c.name = :curriculumName ORDER BY CASE WHEN i.response IS NULL THEN 0 ELSE 1 END, i.createdDate DESC")
    List<ManagerInquiry> findTeacherInquiriesWithCurriculumName(@Param("curriculumName") String curriculumName);

    //문의 목록 커리큘럼 이름 + 기수 기준
    @Query("SELECT i FROM ManagerInquiry i JOIN FETCH i.user u JOIN FETCH u.curriculum c WHERE TYPE(u) = Teacher AND c.name = :curriculumName AND c.th = :curriculumTh ORDER BY CASE WHEN i.response IS NULL THEN 0 ELSE 1 END, i.createdDate DESC")
    List<ManagerInquiry> findTeacherInquiriesWithCurriculumNameAndCurriculumTh(@Param("curriculumName") String curriculumName, @Param("curriculumTh")Long curriculumTh);

    @Query("select count(i) from ManagerInquiry i join i.user u where i.response is null and u.role =:role")
    Integer countInquiryWithOutResponse(@Param("role") Role role);
}