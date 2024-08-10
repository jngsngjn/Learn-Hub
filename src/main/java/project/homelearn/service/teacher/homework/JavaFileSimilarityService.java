package project.homelearn.service.teacher.homework;

import org.apache.commons.text.similarity.CosineSimilarity;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class JavaFileSimilarityService {

    // .java 파일에서 텍스트 추출
    public String extractTextFromJavaFile(String filePath) throws IOException {
        StringBuilder text = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                text.append(line).append("\n");
            }
        }
        return text.toString();
    }

    // 두 .java 파일 간의 유사도 계산
    public double calculateJavaFileSimilarity(String filePath1, String filePath2) throws IOException {
        String text1 = extractTextFromJavaFile(filePath1);
        String text2 = extractTextFromJavaFile(filePath2);

        return calculateTextSimilarity(text1, text2);
    }

    // 텍스트 유사도 계산 (코사인 유사도)
    private double calculateTextSimilarity(String text1, String text2) {
        CosineSimilarity cosineSimilarity = new CosineSimilarity();
        Map<CharSequence, Integer> vector1 = getWordFrequencyMap(text1);
        Map<CharSequence, Integer> vector2 = getWordFrequencyMap(text2);

        return cosineSimilarity.cosineSimilarity(vector1, vector2);
    }

    // 텍스트를 단어 빈도 맵으로 변환
    private Map<CharSequence, Integer> getWordFrequencyMap(String text) {
        Map<CharSequence, Integer> wordFrequencyMap = new HashMap<>();
        String[] words = text.split("\\s+"); // 공백을 기준으로 단어 분리

        for (String word : words) {
            wordFrequencyMap.put(word, wordFrequencyMap.getOrDefault(word, 0) + 1);
        }

        return wordFrequencyMap;
    }
}