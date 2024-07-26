package project.homelearn.entity.manager;

import project.homelearn.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "manager_board")
public class ManagerBoard extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Lob
    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false)
    private boolean emergency;

    //보류
    @Column(name = "file_name")
    private String fileName;

    //보류
    @Column(name = "file_path")
    private String filePath;

}