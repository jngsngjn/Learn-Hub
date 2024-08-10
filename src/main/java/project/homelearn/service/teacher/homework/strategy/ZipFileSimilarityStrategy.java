package project.homelearn.service.teacher.homework.strategy;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.homelearn.entity.homework.StudentHomework;
import project.homelearn.service.common.StorageService;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

@Service
@RequiredArgsConstructor
public class ZipFileSimilarityStrategy implements FileSimilarityStrategy {

    private final StorageService storageService;
    private final TextFileSimilarityStrategy textFileSimilarityStrategy;
    private final PdfFileSimilarityStrategy pdfFileSimilarityStrategy;

    @Override
    public List<List<String>> similarityCheck(List<StudentHomework> studentHomeworks) throws IOException {
        List<List<String>> similarityGroups = new ArrayList<>();
        boolean[] processed = new boolean[studentHomeworks.size()];

        for (int i = 0; i < studentHomeworks.size(); i++) {
            if (processed[i]) {
                continue;
            }

            Map<String, String> extractedFiles1 = extractFilesFromZip(storageService.downloadFile(studentHomeworks.get(i).getFilePath()));

            List<String> currentGroup = new ArrayList<>();
            currentGroup.add(studentHomeworks.get(i).getUser().getName());

            for (int j = i + 1; j < studentHomeworks.size(); j++) {
                if (processed[j]) {
                    continue;
                }

                Map<String, String> extractedFiles2 = extractFilesFromZip(storageService.downloadFile(studentHomeworks.get(j).getFilePath()));

                double overallSimilarity = calculateZipFileSimilarity(extractedFiles1, extractedFiles2);

                if (overallSimilarity >= 0.9) {
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

    private Map<String, String> extractFilesFromZip(InputStream zipInputStream) throws IOException {
        Map<String, String> extractedFiles = new HashMap<>();
        File tempZipFile = Files.createTempFile("temp", ".zip").toFile();
        Files.copy(zipInputStream, tempZipFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

        try (ZipFile zipFile = new ZipFile(tempZipFile)) {
            Enumeration<? extends ZipEntry> entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry zipEntry = entries.nextElement();
                if (!zipEntry.isDirectory()) {
                    String fileName = zipEntry.getName();
                    String fileContent = extractTextFromEntry(zipFile, zipEntry);
                    if (fileContent != null && !fileContent.isEmpty()) {
                        extractedFiles.put(fileName, fileContent);
                    }
                }
            }
        } finally {
            tempZipFile.delete();
        }

        return extractedFiles;
    }

    private String extractTextFromEntry(ZipFile zipFile, ZipEntry zipEntry) throws IOException {
        try (InputStream inputStream = zipFile.getInputStream(zipEntry)) {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len;
            while ((len = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, len);
            }
            byte[] fileData = outputStream.toByteArray();

            String fileName = zipEntry.getName();
            if (fileName.endsWith(".java") || fileName.endsWith(".html") || fileName.endsWith(".js") || fileName.endsWith(".css") || fileName.endsWith(".sql")
                    || fileName.endsWith(".properties") || fileName.endsWith(".yml") || fileName.endsWith(".xml") || fileName.endsWith(".gradle")) {
                return new String(fileData);
            } else if (fileName.endsWith(".pdf")) {
                return pdfFileSimilarityStrategy.extractText(new ByteArrayInputStream(fileData));
            }
        }
        return "";
    }

    private double calculateZipFileSimilarity(Map<String, String> extractedFiles1, Map<String, String> extractedFiles2) {
        double totalSimilarity = 0.0;
        int comparisonCount = 0;

        for (String fileName : extractedFiles1.keySet()) {
            if (extractedFiles2.containsKey(fileName)) {
                String text1 = extractedFiles1.get(fileName);
                String text2 = extractedFiles2.get(fileName);
                double similarity = textFileSimilarityStrategy.calculateTextSimilarity(text1, text2);
                totalSimilarity += similarity;
                comparisonCount++;
            }
        }

        return comparisonCount > 0 ? totalSimilarity / comparisonCount : 0.0;
    }
}