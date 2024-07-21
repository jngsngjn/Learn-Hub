package project.homelearn.entity.notification.admin;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import project.homelearn.entity.inquiry.AdminInquiry;

@Entity
@Getter @Setter
@Table(name = "admin_notification")
public class AdminNotification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inquiry_id", nullable = false)
    private AdminInquiry adminInquiry;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AdminNotificationType type;
}