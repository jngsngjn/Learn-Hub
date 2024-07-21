package project.homelearn.repository.notification;

import org.springframework.data.jpa.repository.JpaRepository;
import project.homelearn.entity.notification.student.StudentNotification;

public interface StudentNotificationRepository extends JpaRepository<StudentNotification, Long> {
}