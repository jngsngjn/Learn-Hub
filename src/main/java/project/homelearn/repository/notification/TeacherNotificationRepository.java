package project.homelearn.repository.notification;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import project.homelearn.entity.notification.teacher.TeacherNotification;
import project.homelearn.entity.user.User;

public interface TeacherNotificationRepository extends JpaRepository<TeacherNotification, Long> {

    @Modifying
    void deleteAllByUser(User user);
}