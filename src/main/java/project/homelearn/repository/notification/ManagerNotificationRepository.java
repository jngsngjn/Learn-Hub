package project.homelearn.repository.notification;

import org.springframework.data.jpa.repository.JpaRepository;
import project.homelearn.entity.notification.manager.ManagerNotification;

public interface ManagerNotificationRepository extends JpaRepository<ManagerNotification, Long> {


}