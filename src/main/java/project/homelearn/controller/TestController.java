package project.homelearn.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.homelearn.dto.TestDto;

@Slf4j
@RestController
@RequestMapping
public class TestController {

    @PostMapping("/test-editor")
    public ResponseEntity<?> test(@ModelAttribute TestDto testDto) {
        log.info("testDto = {}", testDto);
        return new ResponseEntity<>(testDto, HttpStatus.OK);
    }
}