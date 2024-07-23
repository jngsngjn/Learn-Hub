package project.homelearn.controller.manager;

import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.homelearn.dto.manager.ManagerStudentDto;
import project.homelearn.dto.manager.StudentAddDto;
import project.homelearn.service.manager.EmailCodeService;
import project.homelearn.service.manager.ManagerStudentService;

@Slf4j
@RestController
@RequestMapping("/manager")
@RequiredArgsConstructor
public class ManagerStudentController {

    private final EmailCodeService emailCodeService;
    private final ManagerStudentService managerStudentService;

    @GetMapping("/manage-student")
    public Page<ManagerStudentDto> studentList(@RequestParam(name = "page", defaultValue = "0") int page,
                                               @RequestParam(name = "curriculumName", required = false) String curriculumName,
                                               @RequestParam(name = "curriculumTh", required = false) Long curriculumTh){

        Page<ManagerStudentDto> students;
        if (curriculumName != null && !curriculumName.isEmpty()) {
            students = managerStudentService.getStudentsWithCurriculumName(15, page, curriculumName);
        } else {
            students = managerStudentService.getStudents(15, page);
        }

        return students;
    }

    @PostMapping("/manage-student/add")
    public ResponseEntity<?> addStudent(@Valid @RequestBody StudentAddDto studentAddDto) throws MessagingException {
        boolean result = managerStudentService.addStudent(studentAddDto);
        if (result) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}