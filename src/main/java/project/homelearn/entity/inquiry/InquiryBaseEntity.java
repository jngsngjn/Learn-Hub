package project.homelearn.entity.inquiry;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import project.homelearn.entity.BaseEntity;
import project.homelearn.entity.user.User;

import java.time.LocalDateTime;

@Getter @Setter
@MappedSuperclass
public abstract class InquiryBaseEntity extends BaseEntity {

    @Column(nullable = false)
    private String title;

    @Lob
    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Lob
    @Column(columnDefinition = "TEXT", insertable = false)
    private String response;

    @Column(name = "response_date", insertable = false)
    private LocalDateTime responseDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}