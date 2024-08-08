package project.homelearn.service.student;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.homelearn.entity.homework.Homework;
import project.homelearn.entity.notification.student.StudentNotification;
import project.homelearn.entity.student.Student;
import project.homelearn.entity.survey.Survey;
import project.homelearn.repository.notification.StudentNotificationRepository;

import java.util.List;

import static project.homelearn.entity.notification.student.StudentNotificationType.HOMEWORK_UPLOADED;
import static project.homelearn.entity.notification.student.StudentNotificationType.SURVEY;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class StudentNotificationService {

    private final StudentNotificationRepository notificationRepository;

    // 매니저가 설문 등록 시 학생들에게 알림
    public void notifySurvey(Survey survey, List<Student> students) {
        for (Student student : students) {
            StudentNotification notification = new StudentNotification();
            notification.setType(SURVEY);
            notification.setUser(student);
            notification.setSurvey(survey);
            notificationRepository.save(notification);
        }
    }

    /*
    매니저가 설문 마감 시 알림 삭제
    설문 완료 시 알림 삭제
     */
    public void deleteSurveyNotification(Survey survey) {
        notificationRepository.deleteAllBySurvey(survey);
    }

    // 과제 등록 시 학생들에게 알림
    public void homeworkNotify(Homework homework, List<Student> students) {
        for (Student student : students) {
            StudentNotification notification = new StudentNotification();
            notification.setType(HOMEWORK_UPLOADED);
            notification.setUser(student);
            notification.setHomework(homework);
            notificationRepository.save(notification);
        }
    }
}