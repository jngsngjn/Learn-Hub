package project.homelearn.controller.manager;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/manager")
@RequiredArgsConstructor
public class ManagerController {

    @GetMapping
    public String manager() {
        return "Hello, manager!";
    }

    @PostMapping("/curriculum/add")
    public ResponseEntity<?> addCurriculum() {
        return null;
    }
}