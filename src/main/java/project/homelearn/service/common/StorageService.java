package project.homelearn.service.common;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.multipart.MultipartFile;
import project.homelearn.config.storage.FolderType;
import project.homelearn.dto.common.FileDto;
import project.homelearn.entity.curriculum.CurriculumType;
import project.homelearn.entity.user.User;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import static project.homelearn.config.storage.StorageConstants.*;
import static project.homelearn.entity.curriculum.CurriculumType.NCP;

/**
 * Author : 정성진
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class StorageService {

    private final AmazonS3Client amazonS3Client;

    @Value("${bucket.name}")
    private String bucketName;

    public FileDto uploadFile(MultipartFile file, String filePath) {
        return uploadFileProcess(file, filePath);
    }

    private FileDto uploadFileProcess(MultipartFile file, String filePath) {
        if (file == null) {
            return null;
        }

        String originalFileName = file.getOriginalFilename();
        String uploadFileName = getUuidFileName(originalFileName);
        String fullPath = "";

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(file.getSize());
        objectMetadata.setContentType(file.getContentType());

        try (InputStream inputStream = file.getInputStream()) {
            fullPath = filePath + uploadFileName;

            // S3에 파일 업로드
            amazonS3Client.putObject(new PutObjectRequest(bucketName, fullPath, inputStream, objectMetadata).withCannedAcl(CannedAccessControlList.PublicRead));
        } catch (IOException e) {
            log.error("Error uploading file : ", e);
        }

        return FileDto.builder()
                .originalFileName(originalFileName)
                .uploadFileName(uploadFileName)
                .filePath(fullPath)
                .build();
    }

    private String getUuidFileName(String fileName) {
        String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
        return UUID.randomUUID() + "." + ext;
    }

    public String getExtractPathWithinPattern(String pattern, HttpServletRequest request) {
        String fullPath = request.getRequestURI();
        AntPathMatcher pathMatcher = new AntPathMatcher();
        return pathMatcher.extractPathWithinPattern(pattern, fullPath);
    }

    public String getContentType(S3Object s3Object, String filePath) {
        String contentType;
        Tika tika = new Tika();
        try {
            contentType = tika.detect(s3Object.getObjectContent());
        } catch (IOException e) {
            log.error("Error detecting content type for file: {}", filePath, e);
            contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
        }
        return contentType;
    }

    public void deleteFile(String filePath) {
        try {
            amazonS3Client.deleteObject(bucketName, filePath);
            log.info("File deleted successfully from S3: {}", filePath);
        } catch (AmazonServiceException e) {
            log.error("Failed to delete file from S3: {}", filePath, e);
        }
    }

    public String getFolderPath(User user, FolderType folderType) {
        Long th = user.getCurriculum().getTh();
        CurriculumType curriculumType = user.getCurriculum().getType();

        StringBuilder folderPath = new StringBuilder();
        if (curriculumType.equals(NCP)) {
            folderPath.append(NCP_STORAGE_PREFIX).append(th);
        } else {
            folderPath.append(AWS_STORAGE_PREFIX).append(th);
        }

        switch (folderType) {
            case FREE_BOARD -> folderPath.append(FREE_BOARD_STORAGE);
            case QUESTION_BOARD -> folderPath.append(QUESTION_BOARD_STORAGE);
            case SUBJECT -> folderPath.append(SUBJECT_STORAGE);
            case HOMEWORK -> folderPath.append(HOMEWORK_STORAGE);
            default -> folderPath.append(PROFILE_STORAGE);
        }

        return folderPath.toString();
    }

    // S3에서 파일 다운로드
    public InputStream downloadFile(String key) {
        S3Object s3Object = amazonS3Client.getObject(new GetObjectRequest(bucketName, key));
        return s3Object.getObjectContent();
    }
}