package project.homelearn.service.manager;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.homelearn.dto.manager.dashboard.SurveyDashboardDto;
import project.homelearn.dto.manager.manage.curriculum.CurriculumSurveyDto;
import project.homelearn.dto.manager.survey.CurriculumAndSurveyDto;
import project.homelearn.dto.manager.survey.SurveyChoiceStatisticsDto;
import project.homelearn.entity.curriculum.Curriculum;
import project.homelearn.entity.student.Student;
import project.homelearn.entity.survey.Survey;
import project.homelearn.repository.curriculum.CurriculumRepository;
import project.homelearn.repository.survey.SurveyRepository;
import project.homelearn.repository.user.StudentRepository;
import project.homelearn.service.student.StudentNotificationService;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ManagerSurveyService {

    private final SurveyRepository surveyRepository;
    private final StudentRepository studentRepository;
    private final CurriculumRepository curriculumRepository;
    private final StudentNotificationService studentNotificationService;

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

            int round = surveyRepository.findSurveyCount(curriculumId) + 1;

            Survey survey = new Survey();

            // 네이버 클라우드 데브옵스 과정 만족도 설문 조사 1회
            survey.setTitle(curriculum.getName() + " 만족도 설문 조사 " + round + "차");
            survey.setCurriculum(curriculum);
            survey.setRound(round);
            surveyRepository.save(survey);

            studentRepository.updateSurveyCompletedFalse(curriculumId);

            List<Student> students = studentRepository.findAllByCurriculum(curriculum);
            studentNotificationService.notifySurvey(survey, students);
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

        Survey survey = surveyRepository.findById(id).orElseThrow();
        studentNotificationService.deleteSurveyNotification(survey);
        return true;
    }

    public List<SurveyDashboardDto> getRecentSurvey() {
        return surveyRepository.findSurveyTop2Dto();
    }

    public CurriculumSurveyDto getProgressSurvey(Long curriculumId) {
        return surveyRepository.findProgressSurvey(curriculumId);
    }

    public List<CurriculumSurveyDto> getEndSurvey(Long curriculumId) {
        return surveyRepository.findEndSurvey(curriculumId);
    }

    public Map<Integer, Integer> getSurveyBasicTrend(Long curriculumId) {
        return surveyRepository.findSurveyBasicTrend(curriculumId);
    }

    public CurriculumAndSurveyDto getCurriculumAndSurvey(Long curriculumId, Long surveyId) {
        return surveyRepository.findCurriculumAndSurvey(curriculumId, surveyId);
    }

    public List<SurveyChoiceStatisticsDto> getSurveyChoiceStatistics(Long surveyId) {
        return surveyRepository.findSurveyChoiceStatistics(surveyId);
    }

    public Page<String> getSurveyTextResponse(Long surveyId, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return surveyRepository.findSurveyTextResponse(surveyId, pageRequest);
    }
}