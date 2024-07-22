package project.homelearn.entity.calendar;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import project.homelearn.entity.curriculum.Curriculum;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
@Table(name = "manager_calendar")
public class ManagerCalendar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curriculum_id")
    private Curriculum curriculum;

    @Column(nullable = false)
    private String title;

    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;
}