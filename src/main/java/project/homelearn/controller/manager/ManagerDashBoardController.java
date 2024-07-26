package project.homelearn.controller.manager;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.homelearn.dto.manager.dashboard.CurriculumDto;
import project.homelearn.dto.manager.dashboard.ScheduleDto;
import project.homelearn.service.manager.ManagerCalendarService;
import project.homelearn.service.manager.ManagerCurriculumService;
import project.homelearn.service.manager.ManagerInquiryService;

import java.util.List;

/**
 * 미완성
 * Author : 정성진
 */
@Slf4j
@RestController
@RequestMapping("/managers/dash-boards")
@RequiredArgsConstructor
public class ManagerDashBoardController {

    private final ManagerCalendarService calendarService;
    private final ManagerCurriculumService curriculumService;
    private final ManagerInquiryService managerInquiryService;

    // 대시보드 캘린더 조회
    @GetMapping("/calendar")
    public List<ScheduleDto> viewAllCalendar() {
        return calendarService.getAllSchedules();
    }

    // 대시보드 교육 과정 현황 조회 (현재날짜에 출석인원/총인원)
    @GetMapping("/curriculum/{type}")
    public ResponseEntity<?> viewCurriculums(@PathVariable("type") String type) {
        List<CurriculumDto> result = curriculumService.getNCPOrAWSCurriculum(type);
        if (result.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else{
            return new ResponseEntity<>(result, HttpStatus.OK);
        }

    }

    // 학생 1:1 문의 개수 조회 (미답변)
    @GetMapping("/inquiry/{type}")
    public ResponseEntity<?> viewInquiryCountStudent(@PathVariable("type") String role) {
        if (role.equals("ROLE_STUDENT")) {
            Integer result = managerInquiryService.getInquiryCount(role);
            if (result.equals(0)) {
                return ResponseEntity.status(HttpStatus.OK).body(null);
            }
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // 강사 1:1 문의 개수 조회 (미답변)
    @GetMapping("/inquiry/{type}")
    public ResponseEntity<?> viewInquiryCountTeacher(@PathVariable("type") String role) {
        if (role.equals("ROLE_TEACHER")) {
            Integer result = managerInquiryService.getInquiryCount(role);
            if (result.equals(0)) {
                return ResponseEntity.status(HttpStatus.OK).body(null);
            }
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // 최근 설문 2개 조회
}