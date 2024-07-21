package project.homelearn.entity.inquiry;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import project.homelearn.entity.notification.admin.AdminNotification;
import project.homelearn.entity.notification.student.StudentNotification;
import project.homelearn.entity.notification.teacher.TeacherNotification;

import java.util.ArrayList;
import java.util.List;

/**
 * 1:1 문의 : 학생 -> 관리자 or 강사 -> 관리자
 */
@Entity
@Getter @Setter
@Table(name = "admin_inquiry")
public class AdminInquiry extends InquiryBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "adminInquiry", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AdminNotification> adminNotifications = new ArrayList<>();

    @OneToMany(mappedBy = "adminInquiry", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TeacherNotification> teacherNotifications = new ArrayList<>();

    @OneToMany(mappedBy = "adminInquiry", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StudentNotification> studentNotifications = new ArrayList<>();
}