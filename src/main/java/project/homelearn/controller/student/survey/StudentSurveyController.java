package project.homelearn.controller.student.survey;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.homelearn.dto.student.survey.SurveyModalDto;
import project.homelearn.service.student.StudentSurveyService;

import java.security.Principal;
import java.util.Map;

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
            return new ResponseEntity<>("이미 마감된 설문입니다.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /*
    {
        "1": "5",
        "2": "4",
        "3": "5",
        "4": "4",
        "5": "3",
        "6": "훈련장려금이 빨리 들어왔으면 좋겠어요."
    }
     */
    // 설문 참여
    @PostMapping
    public ResponseEntity<?> participateSurvey(@PathVariable("surveyId") Long surveyId,
                                               @RequestBody Map<Long, String> surveyResponses,
                                               Principal principal) {
        boolean result = surveyService.participateSurvey(surveyId, surveyResponses, principal.getName());
        if (result) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}