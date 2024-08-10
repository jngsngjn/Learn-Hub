package project.homelearn.service.teacher.homework;

import lombok.RequiredArgsConstructor;
import org.apache.commons.text.similarity.CosineSimilarity;
import org.springframework.stereotype.Service;
import project.homelearn.entity.homework.StudentHomework;
import project.homelearn.service.common.StorageService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TextFileSimilarityService {

    private final StorageService storageService;

    public List<List<String>> similarityCheckProcess(List<StudentHomework> studentHomeworks) throws IOException {
        List<List<String>> similarityGroups = new ArrayList<>();
        boolean[] processed = new boolean[studentHomeworks.size()];

        for (int i = 0; i < studentHomeworks.size(); i++) {
            if (processed[i]) {
                continue;
            }

            InputStream fileStream1 = storageService.downloadFile(studentHomeworks.get(i).getFilePath());
            String filePath1 = saveTempFile(fileStream1, studentHomeworks.get(i).getStoreFileName());

            List<String> currentGroup = new ArrayList<>();
            currentGroup.add(studentHomeworks.get(i).getUser().getName());

            for (int j = i + 1; j < studentHomeworks.size(); j++) {
                if (processed[j]) {
                    continue;
                }

                InputStream fileStream2 = storageService.downloadFile(studentHomeworks.get(j).getFilePath());
                String filePath2 = saveTempFile(fileStream2, studentHomeworks.get(j).getStoreFileName());

                double similarity = calculateJavaFileSimilarity(filePath1, filePath2);

                Files.deleteIfExists(Paths.get(filePath2)); // 임시 파일 삭제

                if (similarity >= 0.9) {
                    currentGroup.add(studentHomeworks.get(j).getUser().getName());
                    processed[j] = true;
                }
            }

            Files.deleteIfExists(Paths.get(filePath1)); // 임시 파일 삭제

            if (currentGroup.size() > 1) { // 그룹에 2명 이상이 있는 경우에만 추가
                similarityGroups.add(currentGroup);
            }
        }
        return similarityGroups;
    }

    // 임시 파일 저장 메서드
    private String saveTempFile(InputStream inputStream, String fileName) throws IOException {
        String tempFilePath = System.getProperty("java.io.tmpdir") + "/" + fileName;
        Files.copy(inputStream, Paths.get(tempFilePath));
        return tempFilePath;
    }

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