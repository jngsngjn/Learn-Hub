package project.homelearn.entity.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
@Table(name = "login_history")
public class LoginHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login_date_time", nullable = false)
    private LocalDateTime loginDateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public LoginHistory() {
    }

    public LoginHistory(LocalDateTime loginDateTime, User user) {
        this.loginDateTime = loginDateTime;
        this.user = user;
    }
}