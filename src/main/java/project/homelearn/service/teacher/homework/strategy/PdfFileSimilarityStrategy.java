package project.homelearn.service.teacher.homework.strategy;

import org.springframework.stereotype.Service;
import project.homelearn.entity.homework.StudentHomework;

import java.io.IOException;
import java.util.List;

@Service
public class PdfFileSimilarityStrategy implements FileSimilarityStrategy {

    @Override
    public List<List<String>> similarityCheck(List<StudentHomework> studentHomeworks) throws IOException {
        return List.of();
    }
}