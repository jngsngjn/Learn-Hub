package project.homelearn.repository.notification;

import org.springframework.data.jpa.repository.JpaRepository;
import project.homelearn.entity.notification.teacher.TeacherNotification;

public interface TeacherNotificationRepository extends JpaRepository<TeacherNotification, Long> {
}