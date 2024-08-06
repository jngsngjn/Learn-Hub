package project.homelearn.controller.manager.survey;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.homelearn.dto.manager.manage.curriculum.CurriculumSurveyDto;
import project.homelearn.dto.manager.survey.CurriculumAndSurveyDto;
import project.homelearn.dto.manager.survey.CurriculumSimpleDto;
import project.homelearn.dto.manager.survey.SurveyChoiceStatisticsDto;
import project.homelearn.service.manager.ManagerCurriculumService;
import project.homelearn.service.manager.ManagerSurveyService;

import java.util.List;
import java.util.Map;

/**
 * Author : 정성진
 */
@RestController
@RequestMapping("/managers/curriculum/{curriculumId}")
@RequiredArgsConstructor
public class ManagerSurveyViewController {

    private final ManagerSurveyService surveyService;
    private final ManagerCurriculumService curriculumService;

    /**
     * 교육과정의 설문조사 현황 조회
     * 1. 교육과정 일반 정보 - ✅
     * 2. 진행 중인 설문조사 - ✅
     * 3. 종료된 설문조사 - ✅
     * 4. 설문조사 추이 - ✅
     */
    @GetMapping("/survey-status/curriculum-simple")
    public CurriculumSimpleDto viewCurriculumSimple(@PathVariable("curriculumId") Long curriculumId) {
        return curriculumService.getCurriculumSimple(curriculumId);
    }

    @GetMapping("/survey-status/progress")
    public ResponseEntity<?> viewProgressSurvey(@PathVariable("curriculumId") Long curriculumId) {
        CurriculumSurveyDto result = surveyService.getProgressSurvey(curriculumId);
        if (result == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/survey-status/end")
    public ResponseEntity<?> viewEndSurvey(@PathVariable("curriculumId") Long curriculumId) {
        List<CurriculumSurveyDto> result = surveyService.getEndSurvey(curriculumId);
        if (result == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/survey-status/basic-trend")
    public ResponseEntity<?> viewSurveyBasicTrend(@PathVariable("curriculumId") Long curriculumId) {
        Map<Integer, Integer> result = surveyService.getSurveyBasicTrend(curriculumId);
        if (result == null) { // 마감된 설문이 없을 경우
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * 설문조사 상세 조회
     * 1. 교육과정 일반 정보 + 설문조사 일반 정보 - ✅
     * 2. 객관식 통계 - ✅
     * 3. 주관식 페이징 (size = 10) - ✅
     */
    @GetMapping("/survey/{surveyId}/basic")
    public CurriculumAndSurveyDto viewCurriculumAndSurvey(@PathVariable("curriculumId") Long curriculumId,
                                                          @PathVariable("surveyId") Long surveyId) {
        return surveyService.getCurriculumAndSurvey(curriculumId, surveyId);
    }

    @GetMapping("/survey/{surveyId}/choice-response")
    public List<SurveyChoiceStatisticsDto> viewSurveyChoiceStatistics(@PathVariable("surveyId") Long surveyId) {
        return surveyService.getSurveyChoiceStatistics(surveyId);
    }

    @GetMapping("/survey/{surveyId}/text-response")
    public Page<String> viewSurveyTextResponse(@PathVariable("surveyId") Long surveyId,
                                               @RequestParam(name = "page", defaultValue = "0") int page) {
        int size = 10;
        return surveyService.getSurveyTextResponse(surveyId, page, size);
    }
}