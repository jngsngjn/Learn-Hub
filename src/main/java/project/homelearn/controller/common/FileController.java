package project.homelearn.controller.common;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.S3Object;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.homelearn.service.common.StorageService;

/**
 * Author : 정성진
 */
@Slf4j
@RestController
@RequestMapping
@RequiredArgsConstructor
public class FileController {

    private final StorageService storageService;
    private final AmazonS3Client amazonS3Client;

    @Value("${bucket.name}")
    private String bucketName;

    // 이미지 렌더링
    @GetMapping("/image/**")
    public ResponseEntity<InputStreamResource> getImage(HttpServletRequest request) {
        String filePath = storageService.getExtractPathWithinPattern("/image/**", request);
        S3Object s3Object = amazonS3Client.getObject(bucketName, filePath);
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + filePath + "\"")
                .body(new InputStreamResource(s3Object.getObjectContent()));
    }

    // 첨부파일 다운로드
    @GetMapping("/attach-file/**")
    public ResponseEntity<InputStreamResource> getAttachedFile(HttpServletRequest request) {
        String filePath = storageService.getExtractPathWithinPattern("/attach-file/**", request);
        S3Object s3Object = amazonS3Client.getObject(bucketName, filePath);
        String contentType = storageService.getContentType(s3Object, filePath);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filePath + "\"")
                .body(new InputStreamResource(s3Object.getObjectContent()));
    }
}