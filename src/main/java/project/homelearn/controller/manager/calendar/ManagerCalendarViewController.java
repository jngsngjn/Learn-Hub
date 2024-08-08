package project.homelearn.controller.manager.calendar;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.homelearn.service.manager.ManagerCalendarService;

import java.util.Map;

@RestController
@RequestMapping("/managers/calendar")
@RequiredArgsConstructor
public class ManagerCalendarViewController {

    private final ManagerCalendarService calendarService;

    @GetMapping("/modal")
    public Map<String, String> viewCurriculumNameAndColor() {
        return calendarService.getCurriculumNameAndColor();
    }
}

/*
제목을 클릭하면 modal 창이 뜨게 한다 -- 고민점

제목을 빼고 - 내용 형식을 처음에 넣어서 수정 삭제 버튼을 event-info 안에다 넣기  --(프)완료

색 변경 select - 현재 교육과정명(색상)
매니저 개인 일정 : 기타로 분류

일정 정보  - 아이폰 달력 ( 제목 점으로 표기) --(프)완료

일정관리 (스크롤) --(프)완료

밑에 빈칸에 기수 이름과 색상 (스크롤)

공통일정 = 회색으로
 */