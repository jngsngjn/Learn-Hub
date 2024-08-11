package project.homelearn.repository.inquiry;

import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import project.homelearn.entity.curriculum.Curriculum;
import project.homelearn.entity.inquiry.ManagerInquiry;
import project.homelearn.entity.inquiry.TeacherInquiry;

import java.util.List;

public interface TeacherInquiryRepository extends JpaRepository<TeacherInquiry, Long> {

    // 문의 목록 작성시간 순으로 추출
    @Query("SELECT i FROM TeacherInquiry i JOIN FETCH i.user u WHERE TYPE(u) = Student ORDER BY CASE WHEN i.response IS NULL THEN 0 ELSE 1 END, i.createdDate DESC")
    List<TeacherInquiry> findStudentInquiriesAllDefault();

    // 문의 내역 개수 (답변 X)
//    @Query("select count(i) from TeacherInquiry i join i.user u where i.response is null and type(u) = Student ")
    @Query("select count(i) from TeacherInquiry i where i.curriculum =:curriculum and i.response is null")
    Integer findStudentInquiryCount(@Param("curriculum") Curriculum curriculum);

    // 특정 학생이 남긴 문의 내역 목록 -> 답변이 안된거 먼저 최신순으로
    @Query("select i from TeacherInquiry i join fetch i.user u  where u.username =:username order by case when i.response is null then 0 else 1 end, i.createdDate desc")
    List<TeacherInquiry> findStudentInquiry(@Param("username")String username);
}