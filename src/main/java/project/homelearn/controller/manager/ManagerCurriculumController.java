package project.homelearn.controller.manager;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import project.homelearn.dto.manager.CurriculumAddDto;
import project.homelearn.service.manager.ManagerService;

@Slf4j
@RestController
@RequestMapping("/manager")
@RequiredArgsConstructor
public class ManagerCurriculumController {

    private final ManagerService managerService;

    @GetMapping
    public String manager() {
        String role = SecurityContextHolder.getContext().getAuthentication().getAuthorities().iterator().next().getAuthority();
        log.info("role = {}", role);
        return "Hello, manager!";
    }

    @PostMapping("/curriculum/add")
    public ResponseEntity<?> addCurriculum(@Valid @RequestBody CurriculumAddDto curriculumAddDto) {
        log.info("/curriculum/add 접근");
        log.info("CurriculumAddDto = {}", curriculumAddDto);
        boolean result = managerService.addCurriculum(curriculumAddDto);

        if (result) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}