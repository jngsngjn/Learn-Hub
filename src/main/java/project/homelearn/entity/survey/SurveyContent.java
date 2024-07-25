package project.homelearn.entity.survey;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@Table(name = "survey_content")
public class SurveyContent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(name = "question_type", nullable = false)
    private QuestionType questionType;

    @OneToMany(mappedBy = "surveyContent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SurveyAnswer> surveyAnswers = new ArrayList<>();

    public SurveyContent() {
    }

    public SurveyContent(String content, QuestionType questionType) {
        this.content = content;
        this.questionType = questionType;
    }
}