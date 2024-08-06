package project.homelearn.controller.teacher.lecture;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import project.homelearn.dto.teacher.lecture.LectureListDto;
import project.homelearn.dto.teacher.lecture.TeacherLectureViewDto;
import project.homelearn.dto.teacher.subject.SubjectSelectListDto;
import project.homelearn.service.teacher.TeacherLectureService;
import project.homelearn.service.teacher.TeacherSubjectService;

import java.security.Principal;
import java.util.List;

/**
 * Author : 정성진
 */
@RestController
@RequestMapping("/teachers/lectures")
@RequiredArgsConstructor
public class TeacherLectureViewController {

    private final TeacherSubjectService subjectService;
    private final TeacherLectureService teacherLectureService;

    /**
     * 강의 탭 조회
     * 1. 과목 리스트 - ✅
     * 2. 강의 페이징 (size = 15) - ✅
     */
    @GetMapping("/subject-select")
    public List<SubjectSelectListDto> viewSubjectSelectList(Principal principal) {
        return subjectService.getSubjectSelectList(principal.getName());
    }

    // localhost:8080/teachers/lectures?page=0&subjectId=1
    @GetMapping
    public Page<LectureListDto> viewLectureList(Principal principal,
                                                @RequestParam(name = "page", defaultValue = "0") int page,
                                                @RequestParam(name = "subjectId", required = false) Long subjectId) {
        int size = 15;
        return teacherLectureService.getLectureList(principal.getName(), page, size, subjectId);
    }

    /**
     * 특정 강의 조회
     * 1. 강의 일반 정보 - ✅
     * 2. 강의 영상 페이징 (size = 6) - ✅
     */
    @GetMapping("/{lectureId}")
    public TeacherLectureViewDto viewLectureBasic(@PathVariable("lectureId") Long lectureId) {
        return teacherLectureService.getLecture(lectureId);
    }

    @GetMapping("/sub")
    public Page<LectureListDto> viewLectureList(Principal principal,
                                                @RequestParam(name = "page", defaultValue = "0") int page) {
        int size = 6;
        return teacherLectureService.getLectureList(principal.getName(), page, size);
    }
}