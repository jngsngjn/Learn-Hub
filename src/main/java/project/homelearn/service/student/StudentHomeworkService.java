package project.homelearn.service.student;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.homelearn.dto.student.dashboard.ViewHomeworkDto;
import project.homelearn.repository.homework.HomeworkRepository;

import java.util.List;

@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class StudentHomeworkService {

    private final HomeworkRepository homeworkRepository;

    // 미제출우선 최근 2개 과제
    public List<ViewHomeworkDto> getHomeworkTop2(String username){
        return homeworkRepository.findHomeworkTop2(username);
    }
}
