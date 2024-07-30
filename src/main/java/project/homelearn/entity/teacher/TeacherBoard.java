package project.homelearn.entity.teacher;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import project.homelearn.entity.BaseEntity;
import project.homelearn.entity.curriculum.Curriculum;

@Entity
@Getter @Setter
@Table(name = "teacher_board")
public class TeacherBoard extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curriculum_id", nullable = false)
    private Curriculum curriculum;

    @Column(nullable = false)
    private String title;

    @Lob
    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false)
    private boolean emergency;

    @Column(name = "upload_file_name")
    private String uploadFileName;

    @Column(name = "store_file_name")
    private String storeFileName;

    @Column(name = "file_path")
    private String filePath;
}