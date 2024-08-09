package project.homelearn.service.teacher;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.homelearn.entity.board.QuestionBoard;
import project.homelearn.entity.board.comment.QuestionBoardComment;
import project.homelearn.entity.inquiry.TeacherInquiry;
import project.homelearn.entity.notification.teacher.TeacherNotification;
import project.homelearn.entity.teacher.Teacher;
import project.homelearn.repository.notification.TeacherNotificationRepository;

import static project.homelearn.entity.notification.teacher.TeacherNotificationType.*;

/**
 * Author : 정성진
 */
@Service
@RequiredArgsConstructor
public class TeacherNotificationService {

    private final TeacherNotificationRepository notificationRepository;

    public void questionNotify(Teacher teacher, QuestionBoard questionBoard) {
        notificationRepository.save(new TeacherNotification(teacher, QUESTION_POSTED, questionBoard));
    }

    public void questionReplyNotify(Teacher teacher, QuestionBoard questionBoard, QuestionBoardComment reply) {
        notificationRepository.save(new TeacherNotification(teacher, REPLY_TO_COMMENT, questionBoard, reply));
    }

    public void studentInquiryNotify(Teacher teacher, TeacherInquiry teacherInquiry) {
        notificationRepository.save(new TeacherNotification(teacher, STUDENT_INQUIRY_TO_TEACHER, teacherInquiry));
    }
}