package project.homelearn.controller.manager;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.homelearn.dto.manager.MangerTeacherDto;
import project.homelearn.dto.manager.enroll.TeacherEnrollDto;
import project.homelearn.service.manager.ManagerTeacherService;

@Slf4j
@RestController
@RequestMapping("/manager")
@RequiredArgsConstructor
public class ManagerTeacherController {

    private final ManagerTeacherService managerTeacherService;

    @GetMapping("/manage-teacher")
    public Page<MangerTeacherDto> teacherList(@RequestParam(name = "page", defaultValue = "0") int page,
                                              @RequestParam(name = "curriculumName", required = false) String curriculumName){

        Page<MangerTeacherDto> teachers;
        if(curriculumName != null && !curriculumName.isEmpty()){
            teachers = managerTeacherService.getTeachersWithCurriculumName(15, page, curriculumName);
        }
        else{
            teachers = managerTeacherService.getTeachers(15, page);
        }

        return teachers;
    }

    // 강사 등록
    @PostMapping("/manage-teacher/enroll")
    public ResponseEntity<?> enrollTeacher(@Valid @RequestBody TeacherEnrollDto teacherEnrollDto) {
        boolean result = managerTeacherService.enrollTeacher(teacherEnrollDto);
        if (result) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
