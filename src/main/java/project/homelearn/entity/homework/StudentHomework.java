package project.homelearn.entity.homework;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;
import project.homelearn.entity.BaseEntity;
import project.homelearn.entity.user.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Getter @Setter
@Table(name = "student_homework")
public class StudentHomework extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "homework_id", nullable = false)
    private Homework homework;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "upload_file_name")
    private String uploadFileName;

    @Column(name = "store_file_name")
    private String storeFileName;

    @Column(name = "file_path")
    private String filePath;

    @Lob
    @Column(columnDefinition = "TEXT", insertable = false)
    private String response;

    @Column(name = "response_date", insertable = false)
    @UpdateTimestamp
    private LocalDateTime responseDate;
}