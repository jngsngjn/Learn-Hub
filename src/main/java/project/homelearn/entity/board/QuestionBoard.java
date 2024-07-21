package project.homelearn.entity.board;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import project.homelearn.entity.board.comment.QuestionBoardComment;
import project.homelearn.entity.board.scrap.QuestionScrap;
import project.homelearn.entity.curriculum.Subject;
import project.homelearn.entity.notification.student.StudentNotification;
import project.homelearn.entity.notification.teacher.TeacherNotification;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@Table(name = "question_board")
public class QuestionBoard extends BoardBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 기타 질문일 수도 있기 때문에 nullable : true
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @OneToMany(mappedBy = "questionBoard", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<QuestionBoardComment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "questionBoard", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<QuestionScrap> questionScraps = new ArrayList<>();

    @OneToMany(mappedBy = "questionBoard", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TeacherNotification> teacherNotifications = new ArrayList<>();

    @OneToMany(mappedBy = "questionBoard", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StudentNotification> studentNotifications = new ArrayList<>();
}