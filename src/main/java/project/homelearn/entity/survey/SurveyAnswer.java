package project.homelearn.entity.survey;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import project.homelearn.entity.user.User;

@Entity
@Getter @Setter
@Table(name = "survey_answer")
public class SurveyAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "survey_id", nullable = false)
    private Survey survey;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "survey_content_id", nullable = false)
    private SurveyContent surveyContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column
    private Integer rating; // 1점부터 5점까지 선택하는 응답

    @Lob
    @Column(name = "text_answer", columnDefinition = "TEXT")
    private String textAnswer; // 주관식 응답
}