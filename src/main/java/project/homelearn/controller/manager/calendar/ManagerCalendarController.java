package project.homelearn.controller.manager.calendar;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.homelearn.dto.manager.calendar.ManagerScheduleAddDto;
import project.homelearn.service.manager.ManagerCalendarService;

/**
 * Author : 정성진
 */
@Slf4j
@RestController
@RequestMapping("/managers")
@RequiredArgsConstructor
public class ManagerCalendarController {

    private final ManagerCalendarService calendarService;

    // 일정 등록
    @PostMapping("/calendar")
    public ResponseEntity<?> enrollSchedule(@Valid @RequestBody ManagerScheduleAddDto managerScheduleAddDto) {
        boolean result = calendarService.addSchedule(managerScheduleAddDto);
        if (result) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // 일정 수정
    @PatchMapping("/calendar/{id}")
    public ResponseEntity<?> modifySchedule(@PathVariable("id") Long id,
                                            @Valid @RequestBody ManagerScheduleAddDto managerScheduleAddDto) {
        boolean result = calendarService.updateSchedule(id, managerScheduleAddDto);
        if (result) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // 일정 삭제
    @DeleteMapping("/calendar/{id}")
    public ResponseEntity<?> deleteSchedule(@PathVariable("id") Long id) {
        boolean result = calendarService.deleteSchedule(id);
        if (result) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}