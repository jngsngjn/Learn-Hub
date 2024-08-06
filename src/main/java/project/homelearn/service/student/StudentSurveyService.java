package project.homelearn.service.student;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.homelearn.dto.student.survey.SurveyModalDto;
import project.homelearn.repository.survey.SurveyRepository;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class StudentSurveyService {

    private final SurveyRepository surveyRepository;

    public SurveyModalDto getSurveyModal(Long surveyId) {
        boolean finished = surveyRepository.isSurveyFinished(surveyId);
        if (finished) {
            return null;
        }

        return surveyRepository.findSurveyModal(surveyId);
    }
}