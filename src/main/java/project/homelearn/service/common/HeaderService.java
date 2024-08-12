package project.homelearn.service.common;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import project.homelearn.dto.common.HeaderCommonDto;
import project.homelearn.dto.manager.header.NotificationDto;
import project.homelearn.entity.curriculum.Curriculum;
import project.homelearn.entity.notification.manager.ManagerNotification;
import project.homelearn.entity.notification.manager.ManagerNotificationType;
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

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import static project.homelearn.entity.notification.manager.ManagerNotificationType.STUDENT_INQUIRY;
import static project.homelearn.entity.notification.manager.ManagerNotificationType.TEACHER_INQUIRY;
import static project.homelearn.entity.notification.student.StudentNotificationType.*;
import static project.homelearn.entity.notification.student.StudentNotificationType.MANAGER_REPLY_TO_INQUIRY;
import static project.homelearn.entity.notification.teacher.TeacherNotificationType.*;
import static project.homelearn.entity.user.Role.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class HeaderService {

    private final UserRepository userRepository;
    private final ManagerNotificationRepository managerNotificationRepository;
    private final TeacherNotificationRepository teacherNotificationRepository;
    private final StudentNotificationRepository studentNotificationRepository;

    public HeaderCommonDto getHeaderCommon(String username) {
        User user = userRepository.findUserAndCurriculum(username);
        Role role = user.getRole();
        if (role.equals(ROLE_MANAGER)) {
            return null;
        }

        Curriculum curriculum = user.getCurriculum();
        LocalDate startDate = curriculum.getStartDate();
        LocalDate endDate = curriculum.getEndDate();
        LocalDate currentDate = LocalDate.now();

        double progressRate = calculateProgressRate(startDate, endDate, currentDate);
        return HeaderCommonDto
                .builder()
                .curriculumFullName(curriculum.getFullName())
                .name(user.getName())
                .imagePath(user.getImagePath())
                .progressRate(progressRate)
                .build();
    }

    private double calculateProgressRate(LocalDate startDate, LocalDate endDate, LocalDate currentDate) {
        if (currentDate.isBefore(startDate)) {
            return 0.0;
        } else if (currentDate.isAfter(endDate)) {
            return 100.0;
        } else {
            long totalDays = ChronoUnit.DAYS.between(startDate, endDate);
            long daysPassed = ChronoUnit.DAYS.between(startDate, currentDate);
            double progress = (double) daysPassed / totalDays * 100;
            return Math.round(progress * 10) / 10.0;
        }
    }

    public boolean deleteNotification(Long id, String username) {
        User user = userRepository.findByUsername(username);
        Role role = user.getRole();

        if (role.equals(ROLE_STUDENT)) {
            studentNotificationRepository.deleteById(id);
            return true;
        }

        if (role.equals(ROLE_TEACHER)) {
            teacherNotificationRepository.deleteById(id);
            return true;
        }

        if (role.equals(ROLE_MANAGER)) {
            managerNotificationRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean deleteAllNotifications(String username) {
        User user = userRepository.findByUsername(username);
        Role role = user.getRole();

        if (role.equals(ROLE_STUDENT)) {
            studentNotificationRepository.deleteAllByUser(user);
            return true;
        }

        if (role.equals(ROLE_TEACHER)) {
            teacherNotificationRepository.deleteAllByUser(user);
            return true;
        }

        if (role.equals(ROLE_MANAGER)) {
            managerNotificationRepository.deleteAll();
            return true;
        }
        return false;
    }

    public NotificationDto getManagerNotification() {
        List<ManagerNotification> notifications = managerNotificationRepository.findAll();

        NotificationDto result = new NotificationDto();
        result.setCount(notifications.size());

        List<NotificationDto.NotificationSubDto> subList = new ArrayList<>();
        for (ManagerNotification notification : notifications) {
            NotificationDto.NotificationSubDto subDto = new NotificationDto.NotificationSubDto();
            Long inquiryId = notification.getManagerInquiry().getId();

            ManagerNotificationType type = notification.getType();
            if (type.equals(STUDENT_INQUIRY)) {
                subDto.setMessage("학생 1:1 문의가 등록되었습니다.");
                subDto.setUrl("/managers/student-inquiries?inquiryId=" + inquiryId);
            }

            if (type.equals(TEACHER_INQUIRY)) {
                subDto.setMessage("강사 1:1 문의가 등록되었습니다.");
                subDto.setUrl("/managers/teacher-inquiries?inquiryId=" + inquiryId);
            }
            subList.add(subDto);
        }
        result.setNotifications(subList);
        return result;
    }

    public NotificationDto getTeacherNotification(String username) {
        User user = userRepository.findByUsername(username);
        List<TeacherNotification> notifications = teacherNotificationRepository.findAllByUser(user);

        NotificationDto result = new NotificationDto();
        result.setCount(notifications.size());

        List<NotificationDto.NotificationSubDto> subList = new ArrayList<>();
        for (TeacherNotification notification : notifications) {
            NotificationDto.NotificationSubDto subDto = new NotificationDto.NotificationSubDto();

            TeacherNotificationType type = notification.getType();
            if (type.equals(QUESTION_POSTED)) {
                Long questionId = notification.getQuestionBoard().getId();
                subDto.setUrl("/teachers/question-boards/" + questionId);
                subDto.setMessage("질문이 등록되었습니다.");
            }

            if (type.equals(REPLY_TO_COMMENT)) {
                Long questionId = notification.getQuestionBoard().getId();
                Long commentId = notification.getQuestionBoardComment().getId();
                subDto.setUrl("/teachers/question-boards/" + questionId + "?comment=" + commentId);
                subDto.setMessage("질문 답변에 댓글이 등록되었습니다.");
            }

            if (type.equals(STUDENT_INQUIRY_TO_TEACHER)) {
                Long teacherInquiryId = notification.getTeacherInquiry().getId();
                subDto.setUrl("/teachers/student-inquiries/" + teacherInquiryId);
                subDto.setMessage("학생 1:1 문의가 등록되었습니다.");
            }

            if (type.equals(TeacherNotificationType.MANAGER_REPLY_TO_INQUIRY)) {
                Long managerInquiryId = notification.getManagerInquiry().getId();
                subDto.setUrl("/teachers/manager-inquiries/" + managerInquiryId);
                subDto.setMessage("매니저 1:1 문의 답변이 등록되었습니다.");
            }
            subList.add(subDto);
        }
        result.setNotifications(subList);
        return result;
    }

    public NotificationDto getStudentNotification(String username) {
        User user = userRepository.findByUsername(username);
        List<StudentNotification> notifications = studentNotificationRepository.findAllByUser(user);

        NotificationDto result = new NotificationDto();
        result.setCount(notifications.size());

        List<NotificationDto.NotificationSubDto> subList = new ArrayList<>();
        for (StudentNotification notification : notifications) {
            NotificationDto.NotificationSubDto subDto = new NotificationDto.NotificationSubDto();

            StudentNotificationType type = notification.getType();
            if (type.equals(HOMEWORK_UPLOADED)) {
                Long homeworkId = notification.getHomework().getId();
                subDto.setUrl("/students/homeworks/" + homeworkId);
                subDto.setMessage("과제가 등록되었습니다.");
            }

            if (type.equals(REPLY_TO_QUESTION)) {
                Long questionId = notification.getQuestionBoard().getId();
                Long commentId = notification.getQuestionBoardComment().getId();
                subDto.setUrl("/students/question-boards/" + questionId + "?comment=" + commentId);
                subDto.setMessage("질문에 답변이 등록되었습니다.");
            }

            if (type.equals(MANAGER_REPLY_TO_INQUIRY)) {
                Long inquiryId = notification.getManagerInquiry().getId();
                subDto.setUrl("/students/manager-inquiries/" + inquiryId);
                subDto.setMessage("매니저 1:1 문의 답변이 등록되었습니다.");
            }

            if (type.equals(TEACHER_REPLY_TO_INQUIRY)) {
                Long inquiryId = notification.getTeacherInquiry().getId();
                subDto.setUrl("/students/teacher-inquiries/" + inquiryId);
                subDto.setMessage("강사 1:1 문의 답변이 등록되었습니다.");
            }

            if (type.equals(SURVEY)) {
                Long surveyId = notification.getSurvey().getId();
                subDto.setUrl("/students/survey/" + surveyId);
                subDto.setMessage("설문조사를 실시해 주세요.");
            }

            if (type.equals(BADGE)) {
                Long badgeId = notification.getBadge().getId();
                subDto.setUrl("/students/badges?id=" + badgeId);
                subDto.setMessage("새로운 배지를 획득했습니다!");
            }
            subList.add(subDto);
        }
        result.setNotifications(subList);
        return result;
    }
}