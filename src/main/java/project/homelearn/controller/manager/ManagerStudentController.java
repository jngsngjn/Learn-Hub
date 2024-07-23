package project.homelearn.controller.manager;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import project.homelearn.dto.manager.ManagerStudentDto;
import project.homelearn.service.manager.ManagerStudentService;

@Slf4j
@RestController
@RequestMapping("/manager")
@RequiredArgsConstructor
public class ManagerStudentController {

    private final ManagerStudentService managerStudentService;

    @GetMapping("/manage-student")
    public ResponseEntity<Page<ManagerStudentDto>> studentList(@RequestParam(name = "page", defaultValue = "0") int page){
        Page<ManagerStudentDto> students = managerStudentService.getStudents(page, 20);
        return ResponseEntity.ok(students);
    }
}
