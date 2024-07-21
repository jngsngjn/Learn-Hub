package project.homelearn.entity.board.comment;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import project.homelearn.entity.board.QuestionBoard;
import project.homelearn.entity.notification.student.StudentNotification;
import project.homelearn.entity.notification.teacher.TeacherNotification;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@Table(name = "question_board_comment")
public class QuestionBoardComment extends CommentBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_board_id", nullable = false)
    private QuestionBoard questionBoard;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_comment_id")
    private QuestionBoardComment parentComment;

    @OneToMany(mappedBy = "parentComment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<QuestionBoardComment> replies = new ArrayList<>();

    @OneToMany(mappedBy = "questionBoardComment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TeacherNotification> teacherNotifications = new ArrayList<>();

    @OneToMany(mappedBy = "questionBoardComment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StudentNotification> studentNotifications = new ArrayList<>();
}