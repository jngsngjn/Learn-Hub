package project.homelearn.controller.student.lecture;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import project.homelearn.dto.student.lecture.StudentLectureViewDto;
import project.homelearn.dto.teacher.lecture.LectureListDto;
import project.homelearn.dto.teacher.lecture.TeacherLectureViewDto;
import project.homelearn.dto.teacher.subject.SubjectSelectListDto;
import project.homelearn.service.student.StudentLectureService;
import project.homelearn.service.student.StudentSubjectService;
import project.homelearn.service.teacher.TeacherLectureService;
import project.homelearn.service.teacher.TeacherSubjectService;

import java.security.Principal;
import java.util.List;

/**
 * Author : 김승민
 */
@RestController
@RequestMapping("/students/lectures")
@RequiredArgsConstructor
public class StudentLectureViewController {

    private final StudentSubjectService subjectService;
    private final StudentLectureService studentLectureService;

    // 과목 리스트
    @GetMapping("/subject-select")
    public List<SubjectSelectListDto> viewSubjectSelectList(Principal principal) {
        return subjectService.getSubjectSelectList(principal.getName());
    }

    // 강의 페이징 15개
    // localhost:8080/students/lectures?page=0&subjectId=1
    @GetMapping
    public Page<LectureListDto> viewLectureList(Principal principal,
                                                @RequestParam(name = "page", defaultValue = "0") int page,
                                                @RequestParam(name = "subjectId", required = false) Long subjectId) {
        int size = 15;
        return studentLectureService.getLectureList(principal.getName(), page, size, subjectId);
    }


    // 강의 상세보기 페이지
    @GetMapping("/{lectureId}")
    public StudentLectureViewDto viewLectureBasic(@PathVariable("lectureId") Long lectureId) {
        return studentLectureService.getLecture(lectureId);
    }


    // 강의 페이지 6개 처리
    @GetMapping("/sub")
    public Page<LectureListDto> viewLectureList(Principal principal,
                                                @RequestParam(name = "page", defaultValue = "0") int page) {
        int size = 6;
        return studentLectureService.getLectureList(principal.getName(), page, size);
    }
}
