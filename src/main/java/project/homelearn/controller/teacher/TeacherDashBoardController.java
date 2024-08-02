package project.homelearn.controller.teacher;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.homelearn.dto.teacher.dashboard.*;
import project.homelearn.service.manager.ManagerBoardService;
import project.homelearn.service.manager.ManagerCalendarService;
import project.homelearn.service.teacher.NewsService;
import project.homelearn.service.teacher.TeacherCalendarService;
import project.homelearn.service.teacher.TeacherHomeworkService;
import project.homelearn.service.teacher.TeacherInquiryService;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

/**
 * Author : 정성진
 */
@Slf4j
@RestController
@RequestMapping("/teachers/dash-boards")
@RequiredArgsConstructor
public class TeacherDashBoardController {

    private final NewsService newsService;
    private final ManagerBoardService boardService;
    private final TeacherInquiryService inquiryService;
    private final TeacherHomeworkService homeworkService;
    private final ManagerCalendarService managerCalendarService;
    private final TeacherCalendarService teacherCalendarService;

    /**
     * 수강 현황 - 출석 현황
     * 최근 질문 5개
     */

    // 수강 현황 - 과제 제출
    @GetMapping("/homework-state")
    public HomeworkStateDto viewHomeworkState(Principal principal) {
        return homeworkService.getHomeworkState(principal.getName());
    }

    // IT 뉴스 2개
    @GetMapping("/news")
    public List<NewsDto> viewNews() {
        try {
            return newsService.fetchLatestNews();
        } catch (IOException e) {
            log.error("Error fetching news", e);
            return List.of();
        }
    }

    // 학생 1:1 문의 개수
    @GetMapping("/inquiry")
    public ResponseEntity<?> viewInquiryCountStudent(Principal principal) {
        Integer result = inquiryService.getStudentInquiryCount(principal.getName());
        if (result == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // 매니저 공지사항 최신순 4개
    @GetMapping("/manager-boards")
    public List<ManagerBoardDto> viewManagerBoard() {
        return boardService.viewManagerBoardRecent();
    }

    // 매니저가 등록한 일정 중 강사 교육과정에 대한 일정
    @GetMapping("/calendar/manager")
    public List<ScheduleDto> viewManagerSchedule(Principal principal) {
        return managerCalendarService.getAllManagerSchedules(principal.getName());
    }

    // 강사 본인이 등록한 일정
    @GetMapping("/calendar")
    public TeacherScheduleDto viewSchedule(Principal principal) {
        return teacherCalendarService.getSchedule(principal.getName());
    }
}