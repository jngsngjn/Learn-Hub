package project.homelearn.entity.curriculum;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import project.homelearn.entity.user.User;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
@Table(name = "student_lecture")
public class StudentLecture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lecture_id", nullable = false)
    private Lecture lecture;

    @Column(name = "initial_view_date")
    private LocalDateTime initialViewDate;

    @Column(name = "is_completed", nullable = false)
    private boolean isCompleted = false;

    @Column(name = "completed_date")
    private LocalDateTime completedDate;

    @Column(name = "last_position")
    private Long lastPosition; // 마지막으로 시청한 비디오의 재생 위치를 초 단위로 저장
}