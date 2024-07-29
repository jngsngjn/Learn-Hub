package project.homelearn.service.common;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.multipart.MultipartFile;
import project.homelearn.dto.common.FileDto;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

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
            fullPath = filePath + "/" + uploadFileName;

            // S3에 폴더 및 파일 업로드
            amazonS3Client.putObject(new PutObjectRequest(bucketName, fullPath, inputStream, objectMetadata).withCannedAcl(CannedAccessControlList.PublicRead));
        } catch (IOException e) {
            e.printStackTrace();
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

    public String getFilePath(String pattern, HttpServletRequest request) {
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
}