package project.homelearn.entity.homework;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import project.homelearn.entity.BaseEntity;
import project.homelearn.entity.curriculum.Curriculum;
import project.homelearn.entity.notification.student.StudentNotification;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Homework extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curriculum_id", nullable = false)
    private Curriculum curriculum;

    @Column(nullable = false)
    private String title;

    @Lob
    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;

    @Column(name = "required_file", nullable = false)
    private Boolean requiredFile;

    @Enumerated(EnumType.STRING)
    @Column(name = "accept_file")
    private AcceptFile acceptFile;

    @Column(name = "upload_file_name")
    private String uploadFileName;

    @Column(name = "store_file_name")
    private String storeFileName;

    @Column(name = "file_path")
    private String filePath;

    @Column(nullable = false)
    private LocalDateTime deadline;

    @OneToMany(mappedBy = "homework", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StudentHomework> studentHomeworks = new ArrayList<>();

    @OneToMany(mappedBy = "homework", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StudentNotification> studentNotifications = new ArrayList<>();
}