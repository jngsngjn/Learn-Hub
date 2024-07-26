package project.homelearn.entity.student;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import project.homelearn.entity.user.User;

import java.time.LocalDate;

@Entity
@Getter @Setter
@Table(name = "attendance")
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AttendanceType type;

    @Column(nullable = false)
    private LocalDate date;

    public Attendance(User user, AttendanceType type, LocalDate date) {
        this.user = user;
        this.type = type;
        this.date = date;
    }

    public Attendance() {
    }
}