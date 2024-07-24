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
import project.homelearn.dto.manager.manage.StudentUpdateDto;
import project.homelearn.service.manager.ManagerStudentService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/managers")
@RequiredArgsConstructor
public class ManagerStudentController {

    private final ManagerStudentService managerStudentService;

    @GetMapping("/manage-students")
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
    @PostMapping("/manage-students/enroll")
    public ResponseEntity<?> enrollStudent(@Valid @RequestBody StudentEnrollDto studentEnrollDto) {
        boolean result = managerStudentService.enrollStudent(studentEnrollDto);
        if (result) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // 학생 정보 수정
    @PatchMapping("/manage-students/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable("id") Long id,
                                           @Valid @RequestBody StudentUpdateDto studentUpdateDto) {
        boolean result = managerStudentService.updateStudent(id, studentUpdateDto);
        if (result) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // 학생 1명 삭제
    @DeleteMapping("/manage-students/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable("id") Long id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        managerStudentService.deleteStudent(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * 학생 여러 명 삭제
     * Request : JSON, [1, 2, 3, 4, 5]
     */
    @DeleteMapping("/manage-students")
    public ResponseEntity<?> deleteStudents(@RequestBody List<Long> ids) {
        System.out.println("ids = " + ids);
        if (ids == null || ids.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        managerStudentService.deleteStudents(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}