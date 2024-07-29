package project.homelearn.entity.board.comment;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import project.homelearn.entity.board.FreeBoard;
import project.homelearn.entity.notification.student.StudentNotification;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@Table(name = "free_board_comment")
public class FreeBoardComment extends CommentBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "free_board_id", nullable = false)
    private FreeBoard freeBoard;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_comment_id")
    private FreeBoardComment parentComment;

    @OneToMany(mappedBy = "parentComment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FreeBoardComment> replies = new ArrayList<>();

    @OneToMany(mappedBy = "freeBoardComment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StudentNotification> studentNotifications = new ArrayList<>();
}