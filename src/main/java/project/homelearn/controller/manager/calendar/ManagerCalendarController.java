package project.homelearn.controller.manager.calendar;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.homelearn.dto.manager.calendar.ManagerScheduleEnrollDto;
import project.homelearn.service.manager.ManagerCalendarService;

/**
 * Author : 정성진
 */
@Slf4j
@RestController
@RequestMapping("/managers/calendar")
@RequiredArgsConstructor
public class ManagerCalendarController {

    private final ManagerCalendarService calendarService;

    // 일정 등록
    @PostMapping
    public ResponseEntity<?> enrollSchedule(@Valid @RequestBody ManagerScheduleEnrollDto managerScheduleEnrollDto) {
        boolean result = calendarService.addSchedule(managerScheduleEnrollDto);
        if (result) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // 일정 수정
    @PatchMapping("/{calendarId}")
    public ResponseEntity<?> modifySchedule(@PathVariable("calendarId") Long calendarId,
                                            @Valid @RequestBody ManagerScheduleEnrollDto managerScheduleEnrollDto) {
        boolean result = calendarService.updateSchedule(calendarId, managerScheduleEnrollDto);
        if (result) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // 일정 삭제
    @DeleteMapping("/{calendarId}")
    public ResponseEntity<?> deleteSchedule(@PathVariable("calendarId") Long calendarId) {
        boolean result = calendarService.deleteSchedule(calendarId);
        if (result) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}