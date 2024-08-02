package project.homelearn.service.manager;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.homelearn.dto.manager.dashboard.SurveyDto;
import project.homelearn.entity.curriculum.Curriculum;
import project.homelearn.entity.survey.Survey;
import project.homelearn.repository.curriculum.CurriculumRepository;
import project.homelearn.repository.survey.SurveyRepository;
import project.homelearn.repository.user.StudentRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ManagerSurveyService {

    private final SurveyRepository surveyRepository;
    private final StudentRepository studentRepository;
    private final CurriculumRepository curriculumRepository;

    /**
     * 교육 과정 만족도 설문 시작
     * Author : 정성진
     */
    public boolean startSurveyProcess(Long id) {
        try {
            Curriculum curriculum = curriculumRepository
                    .findById(id)
                    .orElseThrow(() -> new NoSuchElementException("Curriculum not found with id: " + id));

            Long curriculumId = curriculum.getId();
            boolean existActiveSurvey = surveyRepository.existActiveSurvey(id);
            if (existActiveSurvey) { // 이미 진행 중인 설문이 있을 때
                return false;
            }

            int result = surveyRepository.findSurveyCount(curriculumId) + 1;

            Survey survey = new Survey();

            // 네이버 클라우드 데브옵스 과정 만족도 설문 조사 1회
            survey.setTitle(curriculum.getName() + " 만족도 설문 조사 " + result + "차");
            survey.setCurriculum(curriculum);
            surveyRepository.save(survey);

            studentRepository.updateSurveyCompletedFalse(curriculumId);
            return true;
        } catch (Exception e) {
            log.error("Error starting survey: ", e);
            return false;
        }
    }

    /**
     * 교육 과정 만족도 설문 마감
     * Author : 정성진
     */
    public boolean stopSurveyProcess(Long id) {
        if (id == null) {
            return false;
        }
        surveyRepository.updateSurveyIsFinishedTrue(id);
        return true;
    }

    public List<SurveyDto> getRecentSurvey() {

    }
}