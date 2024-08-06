package project.homelearn.controller.manager;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.homelearn.dto.manager.dashboard.CurriculumDashboardDto;
import project.homelearn.dto.manager.dashboard.ManagerScheduleDto;
import project.homelearn.dto.manager.dashboard.SurveyDashboardDto;
import project.homelearn.entity.curriculum.CurriculumType;
import project.homelearn.entity.user.Role;
import project.homelearn.service.manager.ManagerCalendarService;
import project.homelearn.service.manager.ManagerCurriculumService;
import project.homelearn.service.manager.ManagerInquiryService;
import project.homelearn.service.manager.ManagerSurveyService;

import java.util.List;

/**
 * Author : 정성진
 */
@Slf4j
@RestController
@RequestMapping("/managers/dash-boards")
@RequiredArgsConstructor
public class ManagerDashBoardController {

    private final ManagerSurveyService surveyService;
    private final ManagerInquiryService inquiryService;
    private final ManagerCalendarService calendarService;
    private final ManagerCurriculumService curriculumService;

    // 대시보드 캘린더 조회
    @GetMapping("/calendar")
    public List<ManagerScheduleDto> viewAllCalendar() {
        return calendarService.getAllSchedules();
    }

    // 대시보드 교육 과정 현황 조회
    @GetMapping("/curriculum/{type}") // NCP or AWS
    public ResponseEntity<?> viewCurriculums(@PathVariable("type") CurriculumType type) {
        List<CurriculumDashboardDto> result = curriculumService.getCurriculumList(type);
        if (result.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
    }

    // 1:1 문의 개수 조회 (미답변)
    @GetMapping("/inquiry/{type}") // ROLE_STUDENT or ROLE_TEACHER
    public ResponseEntity<?> viewInquiryCountStudent(@PathVariable("type") Role role) {
        Integer result = inquiryService.getInquiryCount(role);
        if (result == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // 최근 설문 2개 조회
    @GetMapping("/survey")
    public ResponseEntity<?> viewRecentSurvey() {
        List<SurveyDashboardDto> result = surveyService.getRecentSurvey();
        if (result == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}