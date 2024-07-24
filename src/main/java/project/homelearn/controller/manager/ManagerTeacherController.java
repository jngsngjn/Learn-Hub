package project.homelearn.controller.manager;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import project.homelearn.dto.manager.ManagerStudentDto;
import project.homelearn.dto.manager.MangerTeacherDto;
import project.homelearn.service.manager.ManagerTeacherService;

@Slf4j
@RestController
@RequestMapping("/manager")
@RequiredArgsConstructor
public class ManagerTeacherController {

    private final ManagerTeacherService managerTeacherService;

    @GetMapping("/manage-teacher")
    public Page<MangerTeacherDto> teacherList(@RequestParam(name = "page", defaultValue = "0") int page,
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
        return teachers;
    }
}
