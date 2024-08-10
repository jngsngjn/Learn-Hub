package project.homelearn.service.teacher.homework.strategy;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.text.similarity.CosineSimilarity;
import project.homelearn.entity.homework.StudentHomework;
import project.homelearn.service.common.StorageService;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public abstract class AbstractFileSimilarityStrategy implements FileSimilarityStrategy {

    protected final StorageService storageService;

    protected AbstractFileSimilarityStrategy(StorageService storageService) {
        this.storageService = storageService;
    }

    @Override
    public List<List<String>> similarityCheck(List<StudentHomework> studentHomeworks) throws IOException {
        List<List<String>> similarityGroups = new ArrayList<>();
        boolean[] processed = new boolean[studentHomeworks.size()];

        for (int i = 0; i < studentHomeworks.size(); i++) {
            if (processed[i]) {
                continue;
            }

            String text1 = extractText(storageService.downloadFile(studentHomeworks.get(i).getFilePath()));

            List<String> currentGroup = new ArrayList<>();
            currentGroup.add(studentHomeworks.get(i).getUser().getName());

            for (int j = i + 1; j < studentHomeworks.size(); j++) {
                if (processed[j]) {
                    continue;
                }

                String text2 = extractText(storageService.downloadFile(studentHomeworks.get(j).getFilePath()));

                double similarity = calculateTextSimilarity(text1, text2);
                log.info("파일 {}와 {}의 유사도: {}", studentHomeworks.get(i).getFilePath(), studentHomeworks.get(j).getFilePath(), similarity);

                if (similarity >= 0.9) {
                    log.info("유사도가 90% 이상이므로 같은 그룹으로 묶습니다.");
                    currentGroup.add(studentHomeworks.get(j).getUser().getName());
                    processed[j] = true;
                }
            }

            if (currentGroup.size() > 1) {
                log.info("그룹을 추가합니다: {}", currentGroup);
                similarityGroups.add(currentGroup);
            }
        }
        return similarityGroups;
    }

    protected abstract String extractText(InputStream inputStream) throws IOException;

    protected double calculateTextSimilarity(String text1, String text2) {
        CosineSimilarity cosineSimilarity = new CosineSimilarity();
        Map<CharSequence, Integer> vector1 = getWordFrequencyMap(text1);
        Map<CharSequence, Integer> vector2 = getWordFrequencyMap(text2);

        double similarity = cosineSimilarity.cosineSimilarity(vector1, vector2);
        log.info("계산된 유사도: {}", similarity);
        return similarity;
    }

    private Map<CharSequence, Integer> getWordFrequencyMap(String text) {
        Map<CharSequence, Integer> wordFrequencyMap = new HashMap<>();
        String[] words = text.split("\\s+");

        for (String word : words) {
            wordFrequencyMap.put(word, wordFrequencyMap.getOrDefault(word, 0) + 1);
        }
        return wordFrequencyMap;
    }
}