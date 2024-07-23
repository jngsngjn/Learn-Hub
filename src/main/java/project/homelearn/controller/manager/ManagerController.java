package project.homelearn.controller.manager;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.homelearn.dto.manager.CurriculumAddDto;
import project.homelearn.service.manager.ManagerService;

@RestController
@RequestMapping("/manager")
@RequiredArgsConstructor
public class ManagerController {

    private final ManagerService managerService;

    @GetMapping
    public String manager() {
        return "Hello, manager!";
    }

    @PostMapping("/curriculum/add")
    public ResponseEntity<?> addCurriculum(@Valid @RequestBody CurriculumAddDto curriculumAddDto) {
        boolean result = managerService.addCurriculum(curriculumAddDto);

        if (result) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}