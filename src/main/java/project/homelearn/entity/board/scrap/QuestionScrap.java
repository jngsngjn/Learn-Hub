package project.homelearn.entity.board.scrap;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import project.homelearn.entity.board.QuestionBoard;
import project.homelearn.entity.user.User;

@Entity
@Getter @Setter
@Table(name = "question_scrap")
public class QuestionScrap {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", nullable = false)
    private QuestionBoard questionBoard;

    public QuestionScrap() {
    }

    public QuestionScrap(User user, QuestionBoard questionBoard) {
        this.user = user;
        this.questionBoard = questionBoard;
    }
}