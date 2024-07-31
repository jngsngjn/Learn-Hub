package project.homelearn.controller.teacher.lecture;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.homelearn.dto.teacher.lecture.LectureViewDto;

@Slf4j
@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class LectureViewController {

    @GetMapping
    public ResponseEntity<?> test() {
        LectureViewDto lectureViewDto = new LectureViewDto();
        lectureViewDto.setTitle("Java 설치 for Mac");
        lectureViewDto.setSubject("Java");
        lectureViewDto.setLink("https://www.youtube.com/watch?v=9ThSGVwYZYc");
        return new ResponseEntity<>(lectureViewDto, HttpStatus.OK);
    }
}