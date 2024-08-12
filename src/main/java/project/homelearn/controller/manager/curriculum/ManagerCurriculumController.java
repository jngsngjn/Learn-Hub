package project.homelearn.controller.manager.curriculum;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.homelearn.dto.manager.dashboard.CurriculumDashboardDto;
import project.homelearn.dto.manager.dashboard.ManagerScheduleDto;
import project.homelearn.dto.manager.enroll.CurriculumEnrollDto;
import project.homelearn.dto.manager.enroll.CurriculumEnrollReadyDto;
import project.homelearn.dto.manager.manage.curriculum.*;
import project.homelearn.entity.curriculum.CurriculumType;
import project.homelearn.service.manager.ManagerCalendarService;
import project.homelearn.service.manager.ManagerCurriculumService;

import java.security.Principal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;

@Slf4j
@RestController
@RequestMapping("/managers")
@RequiredArgsConstructor
public class ManagerCurriculumController {

    private final ManagerCalendarService calendarService;
    private final ManagerCurriculumService curriculumService;

    // 교육과정 등록 및 수정 시 사용
    @GetMapping("/enroll-curriculum-ready")
    public CurriculumEnrollReadyDto viewCurriculumEnrollReady() {
        return curriculumService.getCurriculumEnrollReady();
    }

    // 학생 또는 강사 등록 전 사용
    @GetMapping("/enroll-user-ready")
    public List<CurriculumTypeAndTh> readyEnroll() {
        return curriculumService.getCurriculumTypeAndTh();
    }

    /**
     * 교육과정 전체 페이지
     * Author : 김승민
     * */
    @GetMapping("/manage-curriculums/{type}") // NCP or AWS
    public ResponseEntity<?> viewCurriculumList(@PathVariable("type") CurriculumType type) {
        List<CurriculumDashboardDto> result = curriculumService.getCurriculumList(type);
        if (result.isEmpty()) {
            return new ResponseEntity<>("교육과정 없음", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
    }

    // 교육 과정 등록
    @PostMapping("/manage-curriculums/enroll")
    public ResponseEntity<?> enrollCurriculum(@Valid @RequestBody CurriculumEnrollDto curriculumEnrollDto) {
        boolean result = curriculumService.enrollCurriculum(curriculumEnrollDto);

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
        boolean result = curriculumService.updateCurriculum(id, curriculumUpdateDto);
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
        boolean result = curriculumService.checkPassword(username, passwordDto.getPassword());
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
        curriculumService.deleteCurriculum(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * 특정 교육 과정 페이지
     * Author : 정성진
     * 1. 교육 과정 일반 정보 viewCurriculumBasic()
     * 2. 출결 현황 viewCurriculumAttendance()
     * 3. 강사 정보 viewCurriculumTeacher()
     * 4. 캘린더 viewCurriculumCalendar()
     * 5. 설문 조사 viewCurriculumSurvey()
     */
    @GetMapping("/curriculum/{curriculumId}/basic")
    public ResponseEntity<?> viewCurriculumBasic(@PathVariable("curriculumId") Long curriculumId) {
        CurriculumProgressDto result = curriculumService.getCurriculumProgress(curriculumId);
        if (result == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/curriculum/{curriculumId}/attendance")
    public ResponseEntity<?> viewCurriculumAttendance(@PathVariable("curriculumId") Long curriculumId) {
        LocalDate date = LocalDate.now();
        DayOfWeek dayOfWeek = date.getDayOfWeek();

        if (dayOfWeek == SATURDAY || dayOfWeek == SUNDAY) {
            return new ResponseEntity<>(HttpStatus.CONFLICT); // 409
        }

        CurriculumAttendanceDto result = curriculumService.getCurriculumAttendance(curriculumId);
        if (result == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/curriculum/{curriculumId}/teacher")
    public ResponseEntity<?> viewCurriculumTeacher(@PathVariable("curriculumId") Long curriculumId) {
        CurriculumTeacherDto result = curriculumService.getCurriculumTeacherInfo(curriculumId);
        if (result == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/curriculum/{curriculumId}/calendar")
    public ResponseEntity<?> viewCurriculumCalendar(@PathVariable("curriculumId") Long curriculumId) {
        List<ManagerScheduleDto> result = calendarService.getCurriculumSchedules(curriculumId);
        if (result == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/curriculum/{curriculumId}/survey")
    public ResponseEntity<?> viewProgressSurvey(@PathVariable("curriculumId") Long curriculumId) {
        CurriculumSurveyDto survey = curriculumService.getProgressSurvey(curriculumId);
        if (survey == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(survey, HttpStatus.OK);
    }
}