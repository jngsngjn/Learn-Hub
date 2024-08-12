package project.homelearn.controller.student.subject;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import project.homelearn.dto.teacher.dashboard.QuestionTop5Dto;
import project.homelearn.dto.teacher.lecture.LectureListDto;
import project.homelearn.dto.teacher.subject.SubjectBasicDto;
import project.homelearn.dto.teacher.subject.SubjectBoardListDto;
import project.homelearn.dto.teacher.subject.SubjectBoardTop5Dto;
import project.homelearn.dto.teacher.subject.SubjectBoardViewDto;
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

    // 과목 정보
    @GetMapping
    public SubjectBasicDto viewSubjectBasic(@PathVariable("subjectId") Long subjectId) {
        return subjectService.getSubjectBasic(subjectId);
    }

    // 과목 게시판 최신순
    @GetMapping("/boards-recent")
    public List<SubjectBoardTop5Dto> viewSubjectBoardTop5(@PathVariable("subjectId") Long subjectId) {
        return subjectService.getSubjectBoardTop5(subjectId);
    }

    // 질문 게시판 최신순
    @GetMapping("/questions-recent")
    public List<QuestionTop5Dto> viewQuestionTop5(@PathVariable("subjectId") Long subjectId) {
        return subjectService.getQuestionTop5(subjectId);
    }

    // 강의 페이지 6개
    @GetMapping("/lectures")
    public Page<LectureListDto> viewSubjectLecturePage(@PathVariable("subjectId") Long subjectId,
                                                       @RequestParam(name = "page", defaultValue = "0") int page) {
        int size = 6;
        return subjectService.getSubjectLecturePage(subjectId, page, size);
    }

    // 과목 게시판 리스트 페이지
    @GetMapping("/boards")
    public Page<SubjectBoardListDto> viewSubjectBoardPage(@PathVariable("subjectId") Long subjectId,
                                                          @RequestParam(name = "page", defaultValue = "0") int page) {
        int size = 15;
        return subjectService.getSubjectBoardPage(subjectId, page, size);
    }

    // 과목 상세보기
    @GetMapping("/boards/{boardId}")
    public SubjectBoardViewDto viewSubjectBoard(@PathVariable("boardId") Long boardId) {
        return subjectService.getSubjectBoard(boardId);
    }

    // 과목 상세보기 중 리스트 5개
    @GetMapping("/boards-view-list")
    public Page<SubjectBoardListDto> viewSubjectBoard(@PathVariable("subjectId") Long subjectId,
                                                      @RequestParam(name = "page", defaultValue = "0") int page) {
        int size = 5;
        return subjectService.getSubjectBoardPage(subjectId, page, size);
    }
}