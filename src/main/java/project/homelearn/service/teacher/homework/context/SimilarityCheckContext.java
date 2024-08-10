package project.homelearn.service.teacher.homework.context;

import org.springframework.stereotype.Service;
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

@Service
public class SimilarityCheckContext {

    private final Map<AcceptFile, FileSimilarityStrategy> strategies;

    public SimilarityCheckContext(List<FileSimilarityStrategy> strategyList) {
        strategies = new HashMap<>();
        strategies.put(AcceptFile.JAVA, strategyList.stream().filter(s -> s instanceof TextFileSimilarityStrategy).findFirst().orElseThrow());
        strategies.put(AcceptFile.SQL, strategyList.stream().filter(s -> s instanceof TextFileSimilarityStrategy).findFirst().orElseThrow());
        strategies.put(AcceptFile.HTML, strategyList.stream().filter(s -> s instanceof TextFileSimilarityStrategy).findFirst().orElseThrow());
        strategies.put(AcceptFile.JS, strategyList.stream().filter(s -> s instanceof TextFileSimilarityStrategy).findFirst().orElseThrow());
        strategies.put(AcceptFile.TXT, strategyList.stream().filter(s -> s instanceof TextFileSimilarityStrategy).findFirst().orElseThrow());
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