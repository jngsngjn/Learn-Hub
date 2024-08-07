package project.homelearn.repository.notification;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import project.homelearn.entity.notification.student.StudentNotification;
import project.homelearn.entity.survey.Survey;

public interface StudentNotificationRepository extends JpaRepository<StudentNotification, Long> {

    @Modifying
    void deleteAllBySurvey(Survey survey);
}