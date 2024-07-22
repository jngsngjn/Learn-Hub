package project.homelearn.entity.calendar;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import project.homelearn.entity.user.User;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
@Table(name = "teacher_calendar")
public class TeacherCalendar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    private TeacherCalendarColor color;

    @Column(nullable = false)
    private String title;

    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;
}