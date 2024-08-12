package project.homelearn.controller.manager.teacher;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.homelearn.dto.manager.manage.curriculum.CurriculumWithoutTeacherDto;
import project.homelearn.dto.manager.manage.teacher.ManagerTeacherDto;
import project.homelearn.dto.manager.enroll.TeacherEnrollDto;
import project.homelearn.dto.manager.manage.teacher.SpecificTeacherDto;
import project.homelearn.dto.manager.manage.teacher.TeacherUpdateDto;
import project.homelearn.service.manager.ManagerTeacherService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/managers")
@RequiredArgsConstructor
public class ManagerTeacherController {

    private final ManagerTeacherService teacherService;

    // 강사 리스트 조회
    @GetMapping("/manage-teachers")
    public ResponseEntity<?> teacherList(@RequestParam(name = "page", defaultValue = "0") int page,
                                         @RequestParam(name = "curriculumName", required = false) String curriculumName,
                                         @RequestParam(name = "isAssigned", required = false, defaultValue = "true") boolean isAssigned) {
        int size = 15;

        Page<ManagerTeacherDto> teachers;
        if (curriculumName != null && !curriculumName.isEmpty()) {
            teachers = teacherService.getTeachersWithCurriculumName(size, page, curriculumName);
        }
        else {
            if (isAssigned) {
                teachers = teacherService.getTeachers(size, page);
            }
            else{
                teachers = teacherService.getTeachersCurriculumIsNull(size, page);
            }
        }
        if (teachers != null && !teachers.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(teachers);
        }
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    /**
     * Author : 정성진
     * 강사 등록
     */
    @PostMapping("/manage-teachers/enroll")
    public ResponseEntity<?> enrollTeacher(@Valid @RequestBody TeacherEnrollDto teacherEnrollDto) {
        boolean result = teacherService.enrollTeacher(teacherEnrollDto);
        if (result) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Author : 정성진
     * 강사 정보 수정
     */
    @PatchMapping("/manage-teachers/{id}")
    public ResponseEntity<?> updateTeacher(@PathVariable("id") Long id,
                                           @Valid @RequestBody TeacherUpdateDto teacherUpdateDto) {
        boolean result = teacherService.updateTeacher(id, teacherUpdateDto);
        if (result) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Author : 정성진
     * 강사 1명 삭제
     */
    @DeleteMapping("/manage-teachers/{id}")
    public ResponseEntity<?> deleteTeacher(@PathVariable("id") Long id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        teacherService.deleteTeacher(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Author : 정성진
     * 강사 여러 명 삭제
     * Request : JSON, [1, 2, 3, 4, 5]
     */
    @DeleteMapping("/manage-teachers")
    public ResponseEntity<?> deleteTeachers(@RequestBody List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        teacherService.deleteTeachers(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * 특정 강사 페이지
     * 1. 일반 정보 viewTeacherBasic()
     * 2. 강사가 배정되지 않은 교육 과정 viewTeacherWithoutCurriculum()
     */
    @GetMapping("/teacher/basic/{teacherId}")
    public ResponseEntity<?> viewTeacherBasic(@PathVariable("teacherId") Long teacherId) {
        SpecificTeacherDto result = teacherService.getTeacherBasic(teacherId);
        if (result == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/teacher/without-curriculum")
    public ResponseEntity<?> viewTeacherWithoutCurriculum() {
        List<CurriculumWithoutTeacherDto> result = teacherService.getCurriculumWithoutTeacher();
        if (result == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}