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

    // 특정 인원이 매니저에게 남긴 문의 내역 목록 -> 답변이 안된거 먼저 최신순으로
    @Query("select i from ManagerInquiry i join fetch i.user u  where u.username =:username order by case when i.response is null then 0 else 1 end, i.createdDate desc")
    List<ManagerInquiry> findStudentInquiry(@Param("username")String username);
}