package project.homelearn.entity.inquiry;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import project.homelearn.entity.curriculum.Curriculum;
import project.homelearn.entity.notification.student.StudentNotification;
import project.homelearn.entity.notification.teacher.TeacherNotification;

import java.util.ArrayList;
import java.util.List;

/**
 * 1:1 문의 : 학생 -> 강사
 */
@Entity
@Getter @Setter
@Table(name = "teacher_inquiry")
public class TeacherInquiry extends InquiryBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curriculum_id", nullable = false)
    private Curriculum curriculum;

    @OneToMany(mappedBy = "teacherInquiry", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TeacherNotification> teacherNotifications = new ArrayList<>();

    @OneToMany(mappedBy = "teacherInquiry", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StudentNotification> studentNotifications = new ArrayList<>();
}