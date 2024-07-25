package project.homelearn.controller.manager;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.homelearn.dto.manager.dashboard.CurriculumDto;
import project.homelearn.dto.manager.dashboard.ScheduleDto;
import project.homelearn.service.manager.ManagerCalendarService;
import project.homelearn.service.manager.ManagerCurriculumService;

import java.util.List;

/**
 * 미완성
 */
@Slf4j
@RestController
@RequestMapping("/managers/dash-boards")
@RequiredArgsConstructor
public class ManagerDashBoardController {

    private final ManagerCalendarService calendarService;
    private final ManagerCurriculumService curriculumService;

    // 대시보드 캘린더 조회
    @GetMapping("/calendar")
    public List<ScheduleDto> viewAllCalendar() {
        return calendarService.getAllSchedules();
    }

    // 대시보드 교육 과정 현황 조회
    @GetMapping("/curriculum/{type}")
    public List<CurriculumDto> viewCurriculums(@PathVariable("type") String type) {
        return null;
    }

    // 학생 1:1 문의 개수 조회 (미답변)

    // 강사 1:1 문의 개수 조회 (미답변)

    // 최근 설문 2개 조회
}