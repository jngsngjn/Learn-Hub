package project.homelearn.repository.homework.querydsl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import project.homelearn.dto.teacher.dashboard.HomeworkStateDto;
import project.homelearn.dto.teacher.homework.HomeworkTabDto;
import project.homelearn.entity.curriculum.Curriculum;

import java.util.List;

public interface HomeworkRepositoryCustom {

    HomeworkStateDto findHomeworkStateDto(Curriculum curriculum, Integer totalCount);

    Page<HomeworkTabDto> findHomeworks(Curriculum curriculum, Pageable pageable, String status);

    List<Long> findCompletedCount(Long homeworkId);
}