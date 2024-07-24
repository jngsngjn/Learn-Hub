package project.homelearn.controller.manager;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.homelearn.dto.manager.enroll.CurriculumEnrollDto;
import project.homelearn.service.manager.ManagerCurriculumService;

@Slf4j
@RestController
@RequestMapping("/manager")
@RequiredArgsConstructor
public class ManagerCurriculumController {

    private final ManagerCurriculumService managerCurriculumService;

    @GetMapping
    public String manager() {
        return "Hello, manager!";
    }

    @PostMapping("/curriculum/add")
    public ResponseEntity<?> addCurriculum(@Valid @RequestBody CurriculumEnrollDto curriculumEnrollDto) {
        boolean result = managerCurriculumService.enrollCurriculum(curriculumEnrollDto);

        if (result) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}