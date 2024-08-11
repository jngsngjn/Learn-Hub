package project.homelearn.service.teacher.homework.strategy;

import lombok.RequiredArgsConstructor;
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

/**
TextFileSimilarityStrategy와 PdfFileSimilarityStrategy의 코드 중복 개선을 위해 사용
AbstractFileSimilarityStrategy를 상속 받은 클래스만 접근할 수 있도록 protected 접근 제어자 사용
 */
@Slf4j
@RequiredArgsConstructor
public abstract class AbstractFileSimilarityStrategy implements FileSimilarityStrategy {

    protected final StorageService storageService;

    @Override
    public List<List<String>> similarityCheck(List<StudentHomework> studentHomeworks) throws IOException {
        List<List<String>> similarityGroups = new ArrayList<>();  // 유사한 과제를 제출한 학생들을 그룹으로 묶어서 저장할 리스트
        boolean[] processed = new boolean[studentHomeworks.size()];  // 각 과제가 처리되었는지를 추적하는 배열

        for (int i = 0; i < studentHomeworks.size(); i++) {
            if (processed[i]) {
                continue;  // 이미 처리된 과제는 건너뜀
            }

            // 첫 번째 과제 파일에서 텍스트를 추출
            String text1 = extractText(storageService.downloadFile(studentHomeworks.get(i).getFilePath()));

            List<String> currentGroup = new ArrayList<>();  // 현재 그룹에 포함될 학생들의 리스트
            currentGroup.add(studentHomeworks.get(i).getUser().getName());  // 첫 번째 과제 제출 학생을 그룹에 추가

            // 나머지 과제들과 비교하여 유사도를 계산
            for (int j = i + 1; j < studentHomeworks.size(); j++) {
                if (processed[j]) {
                    continue;  // 이미 처리된 과제는 건너뜀
                }

                // 두 번째 과제 파일에서 텍스트를 추출
                String text2 = extractText(storageService.downloadFile(studentHomeworks.get(j).getFilePath()));

                double similarity = calculateTextSimilarity(text1, text2);
                log.info("파일 {}와 {}의 유사도: {}", studentHomeworks.get(i).getFilePath(), studentHomeworks.get(j).getFilePath(), similarity);

                if (similarity >= 0.9) {
                    log.info("유사도가 90% 이상이므로 같은 그룹으로 묶습니다.");
                    currentGroup.add(studentHomeworks.get(j).getUser().getName());
                    processed[j] = true;
                }
            }

            // 그룹에 2명 이상의 학생이 포함된 경우에만 최종 그룹에 추가
            if (currentGroup.size() > 1) {
                log.info("그룹을 추가합니다: {}", currentGroup);
                similarityGroups.add(currentGroup);
            }
        }
        return similarityGroups;
    }

    // 파일에서 텍스트를 추출하는 추상 메서드
    protected abstract String extractText(InputStream inputStream) throws IOException;

    // 두 텍스트 간의 유사도를 코사인 유사도를 사용하여 계산
    protected double calculateTextSimilarity(String text1, String text2) {
        CosineSimilarity cosineSimilarity = new CosineSimilarity();
        Map<CharSequence, Integer> vector1 = getWordFrequencyMap(text1);
        Map<CharSequence, Integer> vector2 = getWordFrequencyMap(text2);

        double similarity = cosineSimilarity.cosineSimilarity(vector1, vector2);
        log.info("계산된 유사도: {}", similarity);
        return similarity;
    }

    // 텍스트를 단어별로 나누고 각 단어의 빈도를 계산하여 맵으로 반환
    private Map<CharSequence, Integer> getWordFrequencyMap(String text) {
        Map<CharSequence, Integer> wordFrequencyMap = new HashMap<>();
        String[] words = text.split("\\s+");  // 텍스트를 공백으로 나눠 단어 배열 생성

        for (String word : words) {
            wordFrequencyMap.put(word, wordFrequencyMap.getOrDefault(word, 0) + 1);
        }
        return wordFrequencyMap;
    }
}