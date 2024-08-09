package project.homelearn.entity.notification.student;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import project.homelearn.entity.board.FreeBoard;
import project.homelearn.entity.board.QuestionBoard;
import project.homelearn.entity.board.comment.FreeBoardComment;
import project.homelearn.entity.board.comment.QuestionBoardComment;
import project.homelearn.entity.homework.Homework;
import project.homelearn.entity.inquiry.ManagerInquiry;
import project.homelearn.entity.inquiry.TeacherInquiry;
import project.homelearn.entity.student.badge.Badge;
import project.homelearn.entity.survey.Survey;
import project.homelearn.entity.user.User;

@Entity
@Getter @Setter
@Table(name = "student_notification")
public class StudentNotification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StudentNotificationType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "homework_id")
    private Homework homework;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private QuestionBoard questionBoard;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_comment_id")
    private QuestionBoardComment questionBoardComment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_inquiry_id")
    private ManagerInquiry managerInquiry;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_inquiry_id")
    private TeacherInquiry teacherInquiry;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "survey_id")
    private Survey survey;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "free_board_id")
    private FreeBoard freeBoard;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "free_board_comment_id")
    private FreeBoardComment freeBoardComment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "badge_id")
    private Badge badge;
}