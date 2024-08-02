package project.homelearn.config.db;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import project.homelearn.entity.manager.Manager;
import project.homelearn.entity.survey.SurveyContent;
import project.homelearn.entity.teacher.Teacher;
import project.homelearn.repository.survey.SurveyContentRepository;
import project.homelearn.repository.user.UserRepository;
import static project.homelearn.entity.survey.QuestionType.RATING;
import static project.homelearn.entity.survey.QuestionType.TEXT;
import static project.homelearn.entity.user.Role.ROLE_TEACHER;
import static project.homelearn.entity.user.Role.ROLE_MANAGER;

@Component
public class DataInitializer implements CommandLineRunner {

    private final String password;
    private final UserRepository userRepository;
    private final SurveyContentRepository surveyContentRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public DataInitializer(@Value("${manager.password}") String password, UserRepository userRepository, SurveyContentRepository surveyContentRepository, BCryptPasswordEncoder passwordEncoder) {
        this.password = password;
        this.userRepository = userRepository;
        this.surveyContentRepository = surveyContentRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        if (!userRepository.existsByUsername("manager")) {
            Manager manager = new Manager();
            manager.setUsername("manager");
            manager.setPassword(passwordEncoder.encode(password));
            manager.setRole(ROLE_MANAGER);
            userRepository.save(manager);
        }

        if (!userRepository.existsByUsername("teacher")) {
            Teacher teacher = new Teacher();
            teacher.setUsername("teacher");
            teacher.setPassword(passwordEncoder.encode(password));
            teacher.setRole(ROLE_TEACHER);
            userRepository.save(teacher);
        }
        if (surveyContentRepository.count() == 0) {
            SurveyContent survey1 = new SurveyContent("교육 내용에 대한 이해도", RATING);
            SurveyContent survey2 = new SurveyContent("강사님의 교육 열의 및 준비성의 만족도", RATING);
            SurveyContent survey4 = new SurveyContent("강사님의 질의응답 만족도", RATING);
            SurveyContent survey3 = new SurveyContent("교육장 환경 및 시설에 대한 만족도", RATING);
            SurveyContent survey5 = new SurveyContent("본 과정을 주위 사람에게 추천하고 싶은 정도", RATING);
            SurveyContent survey6 = new SurveyContent("교육 전반에 대한 소감 및 개선사항에 대한 의견을 적어주세요.", TEXT);

            surveyContentRepository.save(survey1);
            surveyContentRepository.save(survey2);
            surveyContentRepository.save(survey3);
            surveyContentRepository.save(survey4);
            surveyContentRepository.save(survey5);
            surveyContentRepository.save(survey6);
        }
    }
}