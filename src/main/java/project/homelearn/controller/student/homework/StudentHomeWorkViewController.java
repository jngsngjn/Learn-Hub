package project.homelearn.controller.student.homework;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import project.homelearn.dto.student.homework.HomeworkListDto;
import project.homelearn.service.student.StudentHomeworkService;

import java.security.Principal;

/**
 * Author : 김승민
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/students/homeworks")
public class StudentHomeWorkViewController {

    private final StudentHomeworkService studentHomeworkService;

    @GetMapping("/proceeding")
    public ResponseEntity<?> viewProceedingHomework(Principal principal,
                                                    @RequestParam(name = "page", defaultValue = "0") int page){
        int size = 1;

        String username = principal.getName();

        Pageable pageable = PageRequest.of(page, size);

        Page<HomeworkListDto> result = studentHomeworkService.getHomeworkProceeding(username, pageable);

        if (result != null) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("진행중인 과제가 없습니다.",HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/closed")
    public ResponseEntity<?> viewClosedHomework(Principal principal,
                                                @RequestParam(name = "page", defaultValue = "0")int page){
        int size = 3;

        String username = principal.getName();

        Pageable pageable = PageRequest.of(page, size);

        Page<HomeworkListDto> result = studentHomeworkService.getHomeworkClosed(username, pageable);

        if (result != null) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("마감된 과제가 없습니다.",HttpStatus.NOT_FOUND);
        }
    }
}
