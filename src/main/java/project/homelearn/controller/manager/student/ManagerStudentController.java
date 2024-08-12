package project.homelearn.controller.manager.student;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import project.homelearn.dto.manager.enroll.StudentEnrollDto;
import project.homelearn.dto.manager.manage.curriculum.CurriculumProgressDto;
import project.homelearn.dto.manager.manage.student.ManagerStudentDto;
import project.homelearn.dto.manager.manage.student.SpecificStudentDto;
import project.homelearn.dto.manager.manage.student.StudentAttendanceDto;
import project.homelearn.dto.manager.manage.student.StudentUpdateDto;
import project.homelearn.service.manager.ExcelService;
import project.homelearn.service.manager.ManagerStudentService;
import project.homelearn.service.student.AttendanceService;

import java.util.List;

/**
 * Author : 정성진
 */
@Slf4j
@RestController
@RequestMapping("/managers")
@RequiredArgsConstructor
public class ManagerStudentController {

    private final ExcelService excelService;
    private final ManagerStudentService studentService;
    private final AttendanceService attendanceService;

    // 학생 리스트 조회
    @GetMapping("/manage-students")
    public ResponseEntity<?> studentList(@RequestParam(name = "page", defaultValue = "0") int page,
                                         @RequestParam(name = "curriculumName", required = false) String curriculumName,
                                         @RequestParam(name = "curriculumTh", required = false) Long curriculumTh) {
        int size = 15;

        Page<ManagerStudentDto> students;
        if (curriculumTh != null && curriculumName != null && !curriculumName.isEmpty()) {
            students = studentService.getStudentsWithCurriculumNameAndCurriculumTh(size, page, curriculumName, curriculumTh);
        } else if (curriculumName != null && !curriculumName.isEmpty()) {
            students = studentService.getStudentsWithCurriculumName(size, page, curriculumName);
        } else {
            students = studentService.getStudents(size, page);
        }

        if (students != null && !students.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(students);
        }
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    /**
     * 학생 1명 등록
     */
    @PostMapping("/manage-students/enroll")
    public ResponseEntity<?> enrollStudent(@Valid @RequestBody StudentEnrollDto studentEnrollDto) {
        boolean result = studentService.enrollStudent(studentEnrollDto);
        if (result) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * 엑셀 파일로 학생 대량 등록
     */
    @PostMapping("/manage-students/enroll-file")
    public ResponseEntity<?> enrollStudentByFile(MultipartFile file) {
        excelService.importStudentFile(file);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * 학생 정보 수정
     */
    @PatchMapping("/manage-students/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable("id") Long id,
                                           @Valid @RequestBody StudentUpdateDto studentUpdateDto) {
        boolean result = studentService.updateStudent(id, studentUpdateDto);
        if (result) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * 학생 1명 삭제
     */
    @DeleteMapping("/manage-students/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable("id") Long id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        studentService.deleteStudent(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * 학생 여러 명 삭제
     * Request : JSON, [1, 2, 3, 4, 5]
     */
    @DeleteMapping("/manage-students")
    public ResponseEntity<?> deleteStudents(@RequestBody List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        studentService.deleteStudents(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * 특정 학생 페이지
     * 1. 커리큘럼 정보
     * 2. 학생 정보
     * 3. 출결 현황
     */
    @GetMapping("/students/curriculum/{studentId}")
    public ResponseEntity<?> viewStudentCurriculum(@PathVariable("studentId") Long studentId) {
        CurriculumProgressDto result = studentService.getStudentCurriculum(studentId);
        if (result == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/students/basic/{studentId}")
    public ResponseEntity<?> viewStudentBasic(@PathVariable("studentId") Long studentId) {
        SpecificStudentDto result = studentService.getStudentBasic(studentId);
        if (result == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/students/attendance/{studentId}")
    public ResponseEntity<?> viewStudentAttendance(@PathVariable("studentId") Long studentId) {
        StudentAttendanceDto result = attendanceService.getStudentAttendance(studentId);
        if (result == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}