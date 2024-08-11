package project.homelearn.service.teacher.homework.strategy;

import org.springframework.stereotype.Service;
import project.homelearn.service.common.StorageService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

// JAVA, HTML, JS, SQL 등 단순 텍스트 파일 처리
@Service
public class TextFileSimilarityStrategy extends AbstractFileSimilarityStrategy {

    public TextFileSimilarityStrategy(StorageService storageService) {
        super(storageService);
    }

    @Override
    protected String extractText(InputStream inputStream) throws IOException {
        StringBuilder text = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                text.append(line).append("\n");
            }
        }
        return text.toString();
    }
}