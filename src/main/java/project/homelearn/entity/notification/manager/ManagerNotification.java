package project.homelearn.entity.notification.manager;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import project.homelearn.entity.inquiry.ManagerInquiry;

@Entity
@Getter @Setter
@Table(name = "manager_notification")
public class ManagerNotification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inquiry_id", nullable = false)
    private ManagerInquiry managerInquiry;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ManagerNotificationType type;
}