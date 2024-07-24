package project.homelearn.controller.manager;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.homelearn.dto.manager.ManagerStudentDto;
import project.homelearn.dto.manager.enroll.StudentEnrollDto;
import project.homelearn.service.manager.ManagerStudentService;

@Slf4j
@RestController
@RequestMapping("/manager")
@RequiredArgsConstructor
public class ManagerStudentController {

    private final ManagerStudentService managerStudentService;

    @GetMapping("/manage-student")
    public Page<ManagerStudentDto> studentList(@RequestParam(name = "page", defaultValue = "0") int page,
                                               @RequestParam(name = "curriculumName", required = false) String curriculumName,
                                               @RequestParam(name = "curriculumTh", required = false) Long curriculumTh) {

        Page<ManagerStudentDto> students;
        if (curriculumTh != null && curriculumName != null && !curriculumName.isEmpty()) {
            students = managerStudentService.getStudentsWithCurriculumNameAndCurriculumTh(15, page, curriculumName, curriculumTh);
        } else if (curriculumName != null && !curriculumName.isEmpty()) {
            students = managerStudentService.getStudentsWithCurriculumName(15, page, curriculumName);
        } else {
            students = managerStudentService.getStudents(15, page);
        }

        return students;
    }

    // 학생 등록
    @PostMapping("/manage-student/enroll")
    public ResponseEntity<?> enrollStudent(@Valid @RequestBody StudentEnrollDto studentEnrollDto) {
        boolean result = managerStudentService.enrollStudent(studentEnrollDto);
        if (result) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}