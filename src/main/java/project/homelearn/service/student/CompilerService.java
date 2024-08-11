package project.homelearn.service.student;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Service
public class CompilerService {

    private final String apiKey;
    private final RestTemplate restTemplate;

    public CompilerService(@Value("${compiler.key}") String key, RestTemplateBuilder restTemplateBuilder) {
        this.apiKey = key;
        this.restTemplate = restTemplateBuilder.build();
    }

    public String compileCode(String sourceCode, String language) {
        try {
            String encodedSourceCode = Base64.getEncoder().encodeToString(sourceCode.getBytes(StandardCharsets.UTF_8));

            int languageId = getLanguageId(language);

            Map<String, Object> requestPayload = new HashMap<>();
            requestPayload.put("language_id", languageId);
            requestPayload.put("source_code", encodedSourceCode);
            requestPayload.put("stdin", "");

            ObjectMapper objectMapper = new ObjectMapper();
            String jsonPayload = objectMapper.writeValueAsString(requestPayload);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("x-rapidapi-key", apiKey);
            headers.set("x-rapidapi-host", "judge0-ce.p.rapidapi.com");

            HttpEntity<String> requestEntity = new HttpEntity<>(jsonPayload, headers);
            ResponseEntity<Map> responseEntity = restTemplate.postForEntity(
                    "https://judge0-ce.p.rapidapi.com/submissions?base64_encoded=true&wait=true",
                    requestEntity,
                    Map.class
            );

            Map<String, Object> result = responseEntity.getBody();
            String statusDescription = (String) ((Map<String, Object>) result.get("status")).get("description");

            if ("Accepted".equals(statusDescription)) {
                return processResult(result);
            } else {
                // 컴파일 오류나 실행 오류가 발생한 경우 상세 정보를 반환
                StringBuilder errorOutput = new StringBuilder();

                String compileOutput = (String) result.get("compile_output");
                String stderr = (String) result.get("stderr");

                if (compileOutput != null) {
                    String decodedCompileOutput = new String(Base64.getDecoder().decode(compileOutput));
                    errorOutput.append(decodedCompileOutput);
                }
                if (stderr != null) {
                    String decodedStderr = new String(Base64.getDecoder().decode(stderr));
                    errorOutput.append(decodedStderr);
                }

                return errorOutput.toString();
            }

        } catch (IllegalArgumentException e) {
            return "Unsupported language specified: " + e.getMessage();
        } catch (Exception e) {
            return "An internal server error occurred. Please try again later.";
        }
    }

    private int getLanguageId(String language) {
        return switch (language.toLowerCase()) {
            case "java" -> 62;
            case "javascript" -> 63;
            case "python" -> 71;
            default -> throw new IllegalArgumentException("Unsupported language: " + language);
        };
    }

    private String processResult(Map<String, Object> result) {
        StringBuilder output = new StringBuilder();

        String stdout = (String) result.get("stdout");
        String stderr = (String) result.get("stderr");
        String compileOutput = (String) result.get("compile_output");
        String message = (String) result.get("message");

        if (stdout != null) {
            String decodedStdout = new String(Base64.getDecoder().decode(stdout));
            output.append(decodedStdout);
        }
        if (stderr != null) {
            String decodedStderr = new String(Base64.getDecoder().decode(stderr));
            output.append(decodedStderr);
        }
        if (compileOutput != null) {
            String decodedCompileOutput = new String(Base64.getDecoder().decode(compileOutput));
            output.append(decodedCompileOutput);
        }
        if (message != null) {
            output.append(message);
        }
        return output.toString();
    }
}