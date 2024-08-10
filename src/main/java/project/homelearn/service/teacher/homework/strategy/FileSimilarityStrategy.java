package project.homelearn.service.teacher.homework.strategy;

import project.homelearn.entity.homework.StudentHomework;

import java.io.IOException;
import java.util.List;

public interface FileSimilarityStrategy {
    List<List<String>> similarityCheck(List<StudentHomework> studentHomeworks) throws IOException;
}