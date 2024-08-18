package project.homelearn.controller.student.lecture;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import project.homelearn.dto.student.lecture.StudentLectureDto;
import project.homelearn.service.student.StudentLectureService;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/students/lectures")
@RequiredArgsConstructor
public class StudentLectureController {

    private final StudentLectureService studentLectureService;

    @PatchMapping("/last-view")
    public Optional<StudentLectureDto> patchLastView(

            @RequestBody StudentLectureDto studentLectureDto) {

        return studentLectureService.patchLastView(studentLectureDto);
    }
}
