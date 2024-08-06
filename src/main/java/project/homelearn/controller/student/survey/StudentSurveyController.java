package project.homelearn.controller.student.survey;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.homelearn.dto.student.survey.SurveyModalDto;
import project.homelearn.service.student.StudentSurveyService;

/**
 * Author : 정성진
 */
@RestController
@RequestMapping("/students/survey/{surveyId}")
@RequiredArgsConstructor
public class StudentSurveyController {

    private final StudentSurveyService surveyService;

    // 설문 폼 보여주기
    @GetMapping
    public SurveyModalDto viewSurveyModal(@PathVariable("surveyId") Long surveyId) {
        return surveyService.getSurveyModal(surveyId);
    }


    // 설문 참여 시 : 알림 없애기, 마감된 설문이면 막기

}