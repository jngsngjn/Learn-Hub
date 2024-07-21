package project.homelearn.entity.inquiry;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import project.homelearn.entity.notification.manager.ManagerNotification;
import project.homelearn.entity.notification.student.StudentNotification;
import project.homelearn.entity.notification.teacher.TeacherNotification;

import java.util.ArrayList;
import java.util.List;

/**
 * 1:1 문의 : 학생 -> 매니저 or 강사 -> 매니저
 */
@Entity
@Getter @Setter
@Table(name = "manager_inquiry")
public class ManagerInquiry extends InquiryBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "managerInquiry", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ManagerNotification> managerNotifications = new ArrayList<>();

    @OneToMany(mappedBy = "managerInquiry", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TeacherNotification> teacherNotifications = new ArrayList<>();

    @OneToMany(mappedBy = "managerInquiry", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StudentNotification> studentNotifications = new ArrayList<>();
}