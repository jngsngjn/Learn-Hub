package project.homelearn.entity.student.badge;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import project.homelearn.entity.curriculum.Curriculum;
import project.homelearn.entity.user.User;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
@Getter @Setter
@Table(name = "student_badge")
public class StudentBadge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curriculum_id", nullable = false)
    private Curriculum curriculum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "badge_id", nullable = false)
    private Badge badge;

    @CreationTimestamp
    @Column(name = "get_date", updatable = false)
    private LocalDate getDate;

    public StudentBadge() {
    }

    public StudentBadge(User user, Badge badge, Curriculum curriculum) {
        this.user = user;
        this.badge = badge;
        this.curriculum = curriculum;
    }

    public String getFormattedGetDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return getDate != null ? getDate.format(formatter) : null;
    }
}