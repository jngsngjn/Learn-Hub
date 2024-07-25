package project.homelearn.controller.manager.curriculum;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.homelearn.dto.manager.enroll.CurriculumEnrollDto;
import project.homelearn.dto.manager.manage.curriculum.CurriculumUpdateDto;
import project.homelearn.dto.manager.manage.curriculum.PasswordDto;
import project.homelearn.service.manager.ManagerCurriculumService;

import java.security.Principal;

@Slf4j
@RestController
@RequestMapping("/managers")
@RequiredArgsConstructor
public class ManagerCurriculumController {

    private final ManagerCurriculumService managerCurriculumService;

    @GetMapping
    public String manager() {
        return "Hello, manager!";
    }

    // 교육 과정 등록
    @PostMapping("/manage-curriculums/enroll")
    public ResponseEntity<?> enrollCurriculum(@Valid @RequestBody CurriculumEnrollDto curriculumEnrollDto) {
        boolean result = managerCurriculumService.enrollCurriculum(curriculumEnrollDto);

        if (result) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // 교육 과정 수정
    @PatchMapping("/manage-curriculums/{id}")
    public ResponseEntity<?> updateCurriculum(@PathVariable("id") Long id,
                                              @Valid @RequestBody CurriculumUpdateDto curriculumUpdateDto) {
        boolean result = managerCurriculumService.updateCurriculum(id, curriculumUpdateDto);
        if (result) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // 교육 과정 삭제 전 비밀번호 확인
    @PostMapping("/check-password")
    public ResponseEntity<?> checkPassword(@Valid @RequestBody PasswordDto passwordDto,
                                           Principal principal) {
        String username = principal.getName();
        boolean result = managerCurriculumService.checkPassword(username, passwordDto.getPassword());
        if (result) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // 교육 과정 1개 삭제
    @DeleteMapping("/manage-curriculums/{id}")
    public ResponseEntity<?> deleteCurriculum(@PathVariable("id") Long id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        managerCurriculumService.deleteCurriculum(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * 특정 교육 과정 페이지
     * 1. 출결 현황
     * 2. 강사 정보 (이름, 이메일, 전화번호)
     * 3. 캘린더
     * 4. 설문 조사
     */
}