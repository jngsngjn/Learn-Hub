package project.homelearn.repository.inquiry;

import org.springframework.data.jpa.repository.JpaRepository;
import project.homelearn.entity.inquiry.ManagerInquiry;

public interface ManagerInquiryRepository extends JpaRepository<ManagerInquiry, Long> {
}