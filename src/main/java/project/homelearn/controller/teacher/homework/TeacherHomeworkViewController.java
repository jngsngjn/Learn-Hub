package project.homelearn.controller.teacher.homework;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import project.homelearn.dto.teacher.homework.HomeworkTabDto;
import project.homelearn.service.teacher.TeacherHomeworkService;

import java.security.Principal;

/**
 * Author : 정성진
 */
@Slf4j
@RestController
@RequestMapping("/teachers/homeworks")
@RequiredArgsConstructor
public class TeacherHomeworkViewController {

    private final TeacherHomeworkService homeworkService;

    /**
     * 과제 탭
     * 1. 진행 중인 과제 - Page(size = 1) ✅
     * 2. 마감된 과제 - Page(size = 3) ✅
     */
    @GetMapping("/progress")
    public Page<HomeworkTabDto> viewProgressHomeworks(Principal principal,
                                                      @RequestParam(name = "page", defaultValue = "0") int page) {
        int size = 1;

        return homeworkService.getHomeworks(principal.getName(), page, size, "진행");
    }

    @GetMapping("/completed")
    public Page<HomeworkTabDto> viewCompletedHomeworks(Principal principal,
                                                      @RequestParam(name = "page", defaultValue = "0") int page) {
        int size = 3;
        return homeworkService.getHomeworks(principal.getName(), page, size, "마감");
    }
}