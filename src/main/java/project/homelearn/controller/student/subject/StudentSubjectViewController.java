package project.homelearn.controller.student.subject;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import project.homelearn.dto.teacher.dashboard.QuestionTop5Dto;
import project.homelearn.dto.teacher.lecture.LectureListDto;
import project.homelearn.dto.teacher.subject.SubjectBasicDto;
import project.homelearn.dto.teacher.subject.SubjectBoardTop5Dto;
import project.homelearn.service.student.StudentSubjectService;

import java.util.List;


/**
 * Author : 김승민
 */
@RestController
@RequestMapping("/students/subjects/{subjectId}")
@RequiredArgsConstructor
public class StudentSubjectViewController {

    private final StudentSubjectService subjectService;

    @GetMapping
    public SubjectBasicDto viewSubjectBasic(@PathVariable("subjectId") Long subjectId) {
        return subjectService.getSubjectBasic(subjectId);
    }

    @GetMapping("/boards-recent")
    public List<SubjectBoardTop5Dto> viewSubjectBoardTop5(@PathVariable("subjectId") Long subjectId) {
        return subjectService.getSubjectBoardTop5(subjectId);
    }

    @GetMapping("/questions-recent")
    public List<QuestionTop5Dto> viewQuestionTop5(@PathVariable("subjectId") Long subjectId) {
        return subjectService.getQuestionTop5(subjectId);
    }

    @GetMapping("/lectures")
    public Page<LectureListDto> viewSubjectLecturePage(@PathVariable("subjectId") Long subjectId,
                                                       @RequestParam(name = "page", defaultValue = "0") int page) {
        int size = 6;
        return subjectService.getSubjectLecturePage(subjectId, page, size);
    }
}
