package project.homelearn.controller.teacher.calendar;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.homelearn.service.teacher.TeacherCalendarService;

/**
 * Author : 정성진
 */
@Slf4j
@RestController
@RequestMapping("/teachers/calendar")
@RequiredArgsConstructor
public class TeacherCalendarController {

    private final TeacherCalendarService calendarService;

//    // 일정 등록
//    @PostMapping
//    public ResponseEntity<?> enrollSchedule() {
//
//    }

    // 일정 수정

    // 일정 삭제
}