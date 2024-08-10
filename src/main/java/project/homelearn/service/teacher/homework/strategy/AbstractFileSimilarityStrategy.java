package project.homelearn.service.teacher.homework.strategy;

import org.apache.commons.text.similarity.CosineSimilarity;
import project.homelearn.entity.homework.StudentHomework;
import project.homelearn.service.common.StorageService;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

                if (similarity >= 0.9) {
                    currentGroup.add(studentHomeworks.get(j).getUser().getName());
                    processed[j] = true;
                }
            }

            if (currentGroup.size() > 1) {
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

        return cosineSimilarity.cosineSimilarity(vector1, vector2);
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