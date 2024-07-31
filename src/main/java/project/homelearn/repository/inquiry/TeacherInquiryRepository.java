package project.homelearn.repository.inquiry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project.homelearn.entity.inquiry.ManagerInquiry;
import project.homelearn.entity.inquiry.TeacherInquiry;
import project.homelearn.entity.user.Role;

import java.util.List;

public interface TeacherInquiryRepository extends JpaRepository<TeacherInquiry, Long> {

    // 문의 목록 작성시간 순으로 추출
    @Query("SELECT i FROM TeacherInquiry i JOIN FETCH i.user u WHERE TYPE(u) = Student ORDER BY CASE WHEN i.response IS NULL THEN 0 ELSE 1 END, i.createdDate DESC")
    List<TeacherInquiry> findStudentInquiriesAllDefault();

    //문의 내역 개수
    @Query("select count(i) from TeacherInquiry i join i.user u where i.response is null and type(u) = Student ")
    Integer countInquiryWithOutResponse();
}