package project.homelearn.controller.manager.calendar;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.homelearn.dto.manager.calendar.CurriculumNameAndColor;
import project.homelearn.service.manager.ManagerCalendarService;

import java.util.List;

/**
 * Author : 정성진
 */
@RestController
@RequestMapping("/managers/calendar")
@RequiredArgsConstructor
public class ManagerCalendarViewController {

    private final ManagerCalendarService calendarService;

    @GetMapping("/modal")
    public List<CurriculumNameAndColor> viewCurriculumNameAndColor() {
        return calendarService.getCurriculumNameAndColor();
    }
}