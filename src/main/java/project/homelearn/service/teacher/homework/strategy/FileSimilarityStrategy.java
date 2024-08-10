package project.homelearn.service.teacher.homework.strategy;

import project.homelearn.entity.homework.StudentHomework;

import java.io.IOException;
import java.util.List;

// 전략 패턴
public interface FileSimilarityStrategy {

    // 유사도로 그룹핑된 학생 리스트 반환
    List<List<String>> similarityCheck(List<StudentHomework> studentHomeworks) throws IOException;
}