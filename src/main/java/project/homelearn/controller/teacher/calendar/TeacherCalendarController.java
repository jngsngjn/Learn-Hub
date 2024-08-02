package project.homelearn.controller.teacher.calendar;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.homelearn.dto.teacher.calendar.TeacherScheduleEnrollDto;
import project.homelearn.service.teacher.TeacherCalendarService;

import java.security.Principal;

/**
 * Author : 정성진
 */
@Slf4j
@RestController
@RequestMapping("/teachers/calendar")
@RequiredArgsConstructor
public class TeacherCalendarController {

    private final TeacherCalendarService calendarService;

    // 일정 등록
    @PostMapping
    public ResponseEntity<?> enrollSchedule(Principal principal,
                                            @Valid @RequestBody TeacherScheduleEnrollDto scheduleDto) {
        calendarService.addSchedule(principal.getName(), scheduleDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 일정 수정
    @PatchMapping("/{calendarId}")
    public ResponseEntity<?> modifySchedule(Principal principal,
                                            @PathVariable("calendarId") Long calendarId,
                                            @Valid @RequestBody TeacherScheduleEnrollDto scheduleDto) {
        boolean result = calendarService.modifySchedule(principal.getName(), calendarId, scheduleDto);
        if (result) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    // 일정 삭제
    @DeleteMapping("/{calendarId}")
    public ResponseEntity<?> deleteSchedule(Principal principal,
                                            @PathVariable("calendarId") Long calendarId) {
        boolean result = calendarService.deleteSchedule(principal.getName(), calendarId);
        if (result) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}