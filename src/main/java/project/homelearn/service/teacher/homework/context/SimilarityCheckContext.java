package project.homelearn.service.teacher.homework.context;

import org.springframework.stereotype.Component;
import project.homelearn.entity.homework.AcceptFile;
import project.homelearn.entity.homework.StudentHomework;
import project.homelearn.service.teacher.homework.strategy.FileSimilarityStrategy;
import project.homelearn.service.teacher.homework.strategy.PdfFileSimilarityStrategy;
import project.homelearn.service.teacher.homework.strategy.TextFileSimilarityStrategy;
import project.homelearn.service.teacher.homework.strategy.ZipFileSimilarityStrategy;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class SimilarityCheckContext {

    private final Map<AcceptFile, FileSimilarityStrategy> strategies;

    // FileSimilarityStrategy를 구현한 클래스가 주입됨
    public SimilarityCheckContext(List<FileSimilarityStrategy> strategyList) {
        strategies = new HashMap<>();

        // TextFileSimilarityStrategy를 재사용하여 여러 파일 형식에 매핑
        FileSimilarityStrategy fileSimilarityStrategy = strategyList.stream()
                .filter(s -> s instanceof TextFileSimilarityStrategy)
                .findFirst()
                .orElseThrow();

        strategies.put(AcceptFile.JAVA, fileSimilarityStrategy);
        strategies.put(AcceptFile.SQL, fileSimilarityStrategy);
        strategies.put(AcceptFile.HTML, fileSimilarityStrategy);
        strategies.put(AcceptFile.JS, fileSimilarityStrategy);
        strategies.put(AcceptFile.TXT, fileSimilarityStrategy);
        strategies.put(AcceptFile.PDF, strategyList.stream().filter(s -> s instanceof PdfFileSimilarityStrategy).findFirst().orElseThrow());
        strategies.put(AcceptFile.ZIP, strategyList.stream().filter(s -> s instanceof ZipFileSimilarityStrategy).findFirst().orElseThrow());
    }

    public List<List<String>> executeStrategy(AcceptFile acceptFile, List<StudentHomework> studentHomeworks) throws IOException {
        FileSimilarityStrategy strategy = strategies.get(acceptFile);
        if (strategy == null) {
            throw new IllegalArgumentException("유사성 검사를 지원하지 않는 파일 형식입니다.");
        }
        return strategy.similarityCheck(studentHomeworks);
    }
}