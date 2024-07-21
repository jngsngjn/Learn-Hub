package project.homelearn.entity.board;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import project.homelearn.entity.board.comment.FreeBoardComment;
import project.homelearn.entity.notification.student.StudentNotification;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@Table(name = "free_board")
public class FreeBoard extends BoardBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "freeBoard", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FreeBoardComment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "freeBoard", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StudentNotification> studentNotifications = new ArrayList<>();
}