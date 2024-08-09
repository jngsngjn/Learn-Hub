package project.homelearn.entity.curriculum;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import project.homelearn.entity.BaseEntity;
import project.homelearn.entity.calendar.ManagerCalendar;
import project.homelearn.entity.homework.Homework;
import project.homelearn.entity.student.badge.StudentBadge;
import project.homelearn.entity.survey.Survey;
import project.homelearn.entity.teacher.TeacherBoard;
import project.homelearn.entity.user.EnrollList;
import project.homelearn.entity.user.User;
import project.homelearn.entity.vote.Vote;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Curriculum extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name; // ex. 네이버 클라우드 데브옵스 과정

    @Column(name = "full_name", nullable = false, unique = true)
    private String fullName; // ex. 네이버 클라우드 데브옵스 과정 1기

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CurriculumType type; // NCP, AWS

    @Column(nullable = false)
    private Long th; // 기수

    @Column(nullable = false, unique = true)
    private String color; // #FFFFFF

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @OneToMany(mappedBy = "curriculum", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<User> users = new ArrayList<>();

    @OneToMany(mappedBy = "curriculum", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Subject> subjects = new ArrayList<>();

    @OneToMany(mappedBy = "curriculum", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Lecture> lectures = new ArrayList<>();

    @OneToMany(mappedBy = "curriculum", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Homework> homeworks = new ArrayList<>();

    @OneToMany(mappedBy = "curriculum", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Vote> votes = new ArrayList<>();

    @OneToMany(mappedBy = "curriculum", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Survey> surveys = new ArrayList<>();

    @OneToMany(mappedBy = "curriculum", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EnrollList> enrollLists = new ArrayList<>();

    @OneToMany(mappedBy = "curriculum", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ManagerCalendar> managerCalendars = new ArrayList<>();

    @OneToMany(mappedBy = "curriculum", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TeacherBoard> teacherBoards = new ArrayList<>();

    @OneToMany(mappedBy = "curriculum", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StudentBadge> studentBadges = new ArrayList<>();
}