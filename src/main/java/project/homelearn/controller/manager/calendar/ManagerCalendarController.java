package project.homelearn.controller.manager.calendar;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.homelearn.dto.manager.calendar.ScheduleDto;
import project.homelearn.service.manager.ManagerCalendarService;

@Slf4j
@RestController
@RequestMapping("/managers/calendar")
@RequiredArgsConstructor
public class ManagerCalendarController {

    private final ManagerCalendarService calendarService;

    // 대시보드 캘린더 조회

    // 특정 교육과정 캘린더 조회

    // 일정 등록
    @PostMapping
    public ResponseEntity<?> enrollSchedule(@Valid @RequestBody ScheduleDto scheduleDto) {
        boolean result = calendarService.addSchedule(scheduleDto);
        if (result) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // 일정 수정
    @PatchMapping("/{id}")
    public ResponseEntity<?> modifySchedule(@PathVariable("id") Long id,
                                            @Valid @RequestBody ScheduleDto scheduleDto) {
        boolean result = calendarService.updateSchedule(id, scheduleDto);
        if (result) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // 일정 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSchedule(@PathVariable("id") Long id) {
        boolean result = calendarService.deleteSchedule(id);
        if (result) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}