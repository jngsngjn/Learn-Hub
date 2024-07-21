package project.homelearn.repository.inquiry;

import org.springframework.data.jpa.repository.JpaRepository;
import project.homelearn.entity.inquiry.TeacherInquiry;

public interface TeacherInquiryRepository extends JpaRepository<TeacherInquiry, Long> {
}