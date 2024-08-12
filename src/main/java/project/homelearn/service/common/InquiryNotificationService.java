package project.homelearn.service.common;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.homelearn.entity.inquiry.ManagerInquiry;
import project.homelearn.entity.inquiry.TeacherInquiry;
import project.homelearn.entity.notification.manager.ManagerNotification;
import project.homelearn.entity.notification.student.StudentNotification;
import project.homelearn.entity.notification.student.StudentNotificationType;
import project.homelearn.entity.notification.teacher.TeacherNotification;
import project.homelearn.entity.notification.teacher.TeacherNotificationType;
import project.homelearn.entity.user.Role;
import project.homelearn.entity.user.User;
import project.homelearn.repository.notification.ManagerNotificationRepository;
import project.homelearn.repository.notification.StudentNotificationRepository;
import project.homelearn.repository.notification.TeacherNotificationRepository;
import project.homelearn.repository.user.UserRepository;

import static project.homelearn.entity.notification.manager.ManagerNotificationType.STUDENT_INQUIRY;
import static project.homelearn.entity.notification.manager.ManagerNotificationType.TEACHER_INQUIRY;
import static project.homelearn.entity.notification.teacher.TeacherNotificationType.STUDENT_INQUIRY_TO_TEACHER;
import static project.homelearn.entity.user.Role.*;

/**
 * Author : 정성진
 */
@Service
@Transactional
@RequiredArgsConstructor
public class InquiryNotificationService {

    private final UserRepository userRepository;
    private final StudentNotificationRepository studentNotificationRepository;
    private final TeacherNotificationRepository teacherNotificationRepository;
    private final ManagerNotificationRepository managerNotificationRepository;

    // 매니저가 학생 또는 강사의 문의에 답변했을 때 해당 인원에게 알림
    public void notifyManageResponse(ManagerInquiry inquiry) {
        User user = userRepository.findUserByManagerInquiry(inquiry);
        Role role = user.getRole();

        if (role.equals(ROLE_STUDENT)) {
            StudentNotification notification = new StudentNotification();
            notification.setUser(user);
            notification.setManagerInquiry(inquiry);
            notification.setType(StudentNotificationType.MANAGER_REPLY_TO_INQUIRY);
            studentNotificationRepository.save(notification);
        } else {
            TeacherNotification notification = new TeacherNotification();
            notification.setUser(user);
            notification.setManagerInquiry(inquiry);
            notification.setType(TeacherNotificationType.MANAGER_REPLY_TO_INQUIRY);
            teacherNotificationRepository.save(notification);
        }
    }

    // 강사가 학생의 문의에 답변했을 때 해당 인원에게 알림
    public void notifyTeacherResponse(TeacherInquiry teacherInquiry) {
        User user = userRepository.findUserByTeacherInquiry(teacherInquiry);

        StudentNotification notification = new StudentNotification();
        notification.setUser(user);
        notification.setTeacherInquiry(teacherInquiry);
        notification.setType(StudentNotificationType.TEACHER_REPLY_TO_INQUIRY);
        studentNotificationRepository.save(notification);
    }

    // 학생이 매니저에게 문의 등록 시 매니저에게 알림
    public void notifyToMangerStudentInquiry(ManagerInquiry inquiry) {
        ManagerNotification notification = new ManagerNotification();
        notification.setManagerInquiry(inquiry);
        notification.setType(STUDENT_INQUIRY);

        managerNotificationRepository.save(notification);
    }

    // 학생이 강사에게 문의 등록 시 매니저에게 알림
    public void notifyToTeacherStudentInquiry(TeacherInquiry inquiry) {
        TeacherNotification notification = new TeacherNotification();
        notification.setTeacherInquiry(inquiry);
        notification.setType(STUDENT_INQUIRY_TO_TEACHER);

        teacherNotificationRepository.save(notification);
    }

    // 강사가 매니저에게 문의 등록 시 매니저에게 알림
    public void notifyToManagerTeacherInquiry(ManagerInquiry inquiry) {
        ManagerNotification notification = new ManagerNotification();
        notification.setManagerInquiry(inquiry);
        notification.setType(TEACHER_INQUIRY);

        managerNotificationRepository.save(notification);
    }
}