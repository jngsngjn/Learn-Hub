package project.homelearn.service.manager;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.homelearn.entity.inquiry.ManagerInquiry;
import project.homelearn.entity.notification.manager.ManagerNotification;
import project.homelearn.repository.notification.ManagerNotificationRepository;

import static project.homelearn.entity.notification.manager.ManagerNotificationType.STUDENT_INQUIRY;

@Service
@Transactional
@RequiredArgsConstructor
public class ManagerNotificationService {

    private final ManagerNotificationRepository notificationRepository;

    public void notifyStudentInquiry(ManagerInquiry inquiry) {
        ManagerNotification notification = new ManagerNotification();
        notification.setManagerInquiry(inquiry);
        notification.setType(STUDENT_INQUIRY);

        notificationRepository.save(notification);
    }
}