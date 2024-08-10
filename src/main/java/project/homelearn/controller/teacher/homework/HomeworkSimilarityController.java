package project.homelearn.controller.teacher.homework;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.homelearn.entity.homework.StudentHomework;
import project.homelearn.service.common.StorageService;
import project.homelearn.service.teacher.homework.JavaFileSimilarityService;
import project.homelearn.service.teacher.homework.TeacherHomeworkService;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/teachers/homeworks/{homeworkId}/similarity")
@RequiredArgsConstructor
public class HomeworkSimilarityController {

    private final StorageService storageService;
    private final TeacherHomeworkService homeworkService;
    private final JavaFileSimilarityService javaService;

    @GetMapping
    public List<List<String>> calculateSimilarity(@PathVariable Long homeworkId) throws IOException {
        List<StudentHomework> studentHomeworks = homeworkService.getAllStudentHomeworksByHomeworkId(homeworkId);

        if (studentHomeworks.size() < 2) {
            throw new IllegalArgumentException("Not enough submissions to compare.");
        }

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

                double similarity = javaService.calculateJavaFileSimilarity(filePath1, filePath2);

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
}