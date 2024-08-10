package project.homelearn.controller.teacher.homework;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import project.homelearn.dto.teacher.homework.HomeworkDetailDto;
import project.homelearn.dto.teacher.homework.HomeworkSubmitListDto;
import project.homelearn.dto.teacher.homework.HomeworkTabDto;
import project.homelearn.service.teacher.homework.TeacherHomeworkService;

import java.security.Principal;
import java.util.List;

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

    /**
     * 과제 상세 조회
     * 1. 과제 상세 내용 ✅
     * 2. 제출 내용 - List ✅
     */
    @GetMapping("/{homeworkId}/detail")
    public HomeworkDetailDto viewHomeworkDetail(Principal principal,
                                                @PathVariable("homeworkId") Long homeworkId) {
        return homeworkService.getHomeworkDetail(principal.getName(), homeworkId);
    }

    @GetMapping("/{homeworkId}/submit-list")
    public List<HomeworkSubmitListDto> viewHomeworkSubmitList(Principal principal,
                                                              @PathVariable("homeworkId") Long homeworkId) {
        return homeworkService.getHomeworkSubmitList(principal.getName(), homeworkId);
    }
}