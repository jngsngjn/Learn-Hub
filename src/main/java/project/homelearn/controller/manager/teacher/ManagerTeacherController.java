package project.homelearn.controller.manager.teacher;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.homelearn.dto.manager.manage.MangerTeacherDto;
import project.homelearn.dto.manager.enroll.TeacherEnrollDto;
import project.homelearn.dto.manager.manage.teacher.TeacherUpdateDto;
import project.homelearn.service.manager.ManagerTeacherService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/managers")
@RequiredArgsConstructor
public class ManagerTeacherController {

    private final ManagerTeacherService managerTeacherService;

    @GetMapping("/manage-teachers")
    public ResponseEntity<?> teacherList(@RequestParam(name = "page", defaultValue = "0") int page,
                                              @RequestParam(name = "curriculumName", required = false) String curriculumName,
                                              @RequestParam(name = "isAssigned", required = false, defaultValue = "true")boolean isAssigned){

        Page<MangerTeacherDto> teachers;
        if(curriculumName != null && !curriculumName.isEmpty()){
            teachers = managerTeacherService.getTeachersWithCurriculumName(15, page, curriculumName);
        }
        else{
            if(isAssigned){
                teachers = managerTeacherService.getTeachers(15, page);
            }
            else{
                teachers = managerTeacherService.getTeachersCurriculumIsNull(15, page);
            }
        }
        if(teachers != null && !teachers.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body(teachers);
        }
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    // 강사 등록
    @PostMapping("/manage-teachers/enroll")
    public ResponseEntity<?> enrollTeacher(@Valid @RequestBody TeacherEnrollDto teacherEnrollDto) {
        boolean result = managerTeacherService.enrollTeacher(teacherEnrollDto);
        if (result) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // 강사 정보 수정
    @PatchMapping("/manage-teachers/{id}")
    public ResponseEntity<?> updateTeacher(@PathVariable("id") Long id,
                                           @Valid @RequestBody TeacherUpdateDto teacherUpdateDto) {
        boolean result = managerTeacherService.updateTeacher(id, teacherUpdateDto);
        if (result) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // 강사 1명 삭제
    @DeleteMapping("/manage-teachers/{id}")
    public ResponseEntity<?> deleteTeacher(@PathVariable("id") Long id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        managerTeacherService.deleteTeacher(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * 강사 여러 명 삭제
     * Request : JSON, [1, 2, 3, 4, 5]
     */
    @DeleteMapping("/manage-teachers")
    public ResponseEntity<?> deleteTeachers(@RequestBody List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        managerTeacherService.deleteTeachers(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}