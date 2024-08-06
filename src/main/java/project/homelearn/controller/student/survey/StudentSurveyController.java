package project.homelearn.controller.student.survey;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> viewSurveyModal(@PathVariable("surveyId") Long surveyId) {
        SurveyModalDto result = surveyService.getSurveyModal(surveyId);
        if (result == null) {
            return new ResponseEntity<>("이미 마감된 설문입니다.",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // 설문 참여 시 : 알림 없애기, 마감된 설문이면 막기

}