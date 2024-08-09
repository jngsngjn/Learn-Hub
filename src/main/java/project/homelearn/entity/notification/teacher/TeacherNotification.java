package project.homelearn.entity.notification.teacher;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import project.homelearn.entity.board.QuestionBoard;
import project.homelearn.entity.board.comment.QuestionBoardComment;
import project.homelearn.entity.inquiry.ManagerInquiry;
import project.homelearn.entity.inquiry.TeacherInquiry;
import project.homelearn.entity.user.User;

@Entity
@Getter @Setter
@Table(name = "teacher_notification")
@NoArgsConstructor
public class TeacherNotification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TeacherNotificationType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private QuestionBoard questionBoard;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_comment_id")
    private QuestionBoardComment questionBoardComment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_inquiry_id")
    private TeacherInquiry teacherInquiry;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_inquiry_id")
    private ManagerInquiry managerInquiry;

    public TeacherNotification(User user, TeacherNotificationType type, QuestionBoard questionBoard) {
        this.user = user;
        this.type = type;
        this.questionBoard = questionBoard;
    }

    public TeacherNotification(User user, TeacherNotificationType type, QuestionBoard questionBoard, QuestionBoardComment questionBoardComment) {
        this.user = user;
        this.type = type;
        this.questionBoard = questionBoard;
        this.questionBoardComment = questionBoardComment;
    }

    public TeacherNotification(User user, TeacherNotificationType type, TeacherInquiry teacherInquiry) {
        this.user = user;
        this.type = type;
        this.teacherInquiry = teacherInquiry;
    }

    public TeacherNotification(User user, TeacherNotificationType type, ManagerInquiry managerInquiry) {
        this.user = user;
        this.type = type;
        this.managerInquiry = managerInquiry;
    }
}