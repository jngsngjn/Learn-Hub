package project.homelearn.service.student;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.homelearn.dto.student.survey.SurveyModalDto;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class StudentSurveyService {

    public SurveyModalDto getSurveyModal(Long surveyId) {
        return null;
    }
}