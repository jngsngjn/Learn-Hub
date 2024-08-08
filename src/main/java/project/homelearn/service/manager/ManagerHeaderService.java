package project.homelearn.service.manager;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.homelearn.dto.manager.header.NotificationDto;
import project.homelearn.entity.notification.manager.ManagerNotification;
import project.homelearn.entity.notification.manager.ManagerNotificationType;
import project.homelearn.repository.notification.ManagerNotificationRepository;

import java.util.ArrayList;
import java.util.List;

import static project.homelearn.dto.manager.header.NotificationDto.NotificationSubDto;
import static project.homelearn.entity.notification.manager.ManagerNotificationType.STUDENT_INQUIRY;
import static project.homelearn.entity.notification.manager.ManagerNotificationType.TEACHER_INQUIRY;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ManagerHeaderService {

    private final ManagerNotificationRepository notificationRepository;

    public NotificationDto getNotification() {
        List<ManagerNotification> notifications = notificationRepository.findAll();

        NotificationDto result = new NotificationDto();
        result.setCount(notifications.size());

        List<NotificationSubDto> subList = new ArrayList<>();
        for (ManagerNotification notification : notifications) {
            NotificationSubDto subDto = new NotificationSubDto();
            subDto.setInquiryId(notification.getManagerInquiry().getId());

            ManagerNotificationType type = notification.getType();
            if (type.equals(STUDENT_INQUIRY)) {
                subDto.setMessage("학생 1:1 문의가 등록되었습니다.");
            }

            if (type.equals(TEACHER_INQUIRY)) {
                subDto.setMessage("강사 1:1 문의가 등록되었습니다.");
            }
            subList.add(subDto);
        }
        result.setNotifications(subList);
        return result;
    }
}