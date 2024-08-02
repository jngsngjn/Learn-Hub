package project.homelearn.repository.homework.querydsl;

import project.homelearn.dto.teacher.dashboard.HomeworkStateDto;
import project.homelearn.entity.curriculum.Curriculum;

public interface HomeworkRepositoryCustom {

    HomeworkStateDto findHomeworkStateDto(Curriculum curriculum, Integer totalCount);
}