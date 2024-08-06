package project.homelearn.controller.manager.survey;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.homelearn.dto.manager.survey.CurriculumSimpleDto;
import project.homelearn.service.manager.ManagerCurriculumService;

/**
 * Author : 정성진
 */
@RestController
@RequestMapping("/managers/curriculum/{curriculumId}")
@RequiredArgsConstructor
public class ManagerSurveyViewController {

    private final ManagerCurriculumService curriculumService;

    /**
     * 교육과정의 설문조사 현황 조회
     * 1. 교육과정 일반 정보 - ✅
     * 2. 진행 중인 설문조사
     * 3. 종료된 설문조사
     * 4. 설문조사 추이
     */
    @GetMapping("/survey-status/curriculum-simple")
    public CurriculumSimpleDto viewCurriculumSimple(@PathVariable("curriculumId") Long curriculumId) {
        return curriculumService.getCurriculumSimple(curriculumId);
    }



    /**
     * 설문조사 조회
     * 1. 교육과정 일반 정보 + 설문조사 일반 정보
     * 2. 객관식
     * 3. 주관식 페이징 (size = 10)
     */
//    @GetMapping("/survey/{surveyId}")
}