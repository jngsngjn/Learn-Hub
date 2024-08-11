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
import project.homelearn.service.teacher.*;
import project.homelearn.service.teacher.homework.TeacherHomeworkService;

import java.io.IOException;
import java.security.Principal;
import java.time.DayOfWeek;
import java.time.LocalDate;
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
    private final TeacherAttendanceService attendanceService;
    private final TeacherQuestionBoardService questionService;

    // 수강 현황 - 출석 현황
    @GetMapping("/attendance-state")
    public ResponseEntity<?> viewAttendanceState(Principal principal) {
        String username = principal.getName();

        // 교육과정 시작 전
        boolean curriculumStarted = attendanceService.isCurriculumStarted(username);
        if (!curriculumStarted) {
            return new ResponseEntity<>("교육과정 시작 전", HttpStatus.BAD_REQUEST);
        }

        LocalDate now = LocalDate.now();
        DayOfWeek dayOfWeek = now.getDayOfWeek();

        // 주말인 경우
        if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
            return new ResponseEntity<>("주말", HttpStatus.BAD_REQUEST);
        }

        AttendanceStateDto attendanceState = attendanceService.getAttendanceState(username);
        return new ResponseEntity<>(attendanceState, HttpStatus.OK);
    }

    // 수강 현황 - 과제 제출
    @GetMapping("/homework-state")
    public HomeworkStateDto viewHomeworkState(Principal principal) {
        return homeworkService.getHomeworkState(principal.getName());
    }

    // 최근 질문 5개
    @GetMapping("/questions")
    public List<QuestionTop5Dto> viewQuestionTop5(Principal principal) {
        return questionService.getQuestionTop5(principal.getName());
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
}