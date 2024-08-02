package project.homelearn.controller.teacher;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.homelearn.dto.teacher.dashboard.NewsDto;
import project.homelearn.service.teacher.NewsService;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/teachers")
@RequiredArgsConstructor
public class TeacherDashBoardController {

    private final NewsService newsService;

    @GetMapping("/news")
    public List<NewsDto> viewNews() {
        try {
            return newsService.fetchLatestNews();
        } catch (IOException e) {
            log.error("Error fetching news", e);
            return List.of();
        }
    }
}