package project.homelearn.controller.student.lecture;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * Author : 정성진
 */
@RestController
@RequestMapping("/students/compile")
@RequiredArgsConstructor
public class CompilerController {

    private final WebClient webClient;

    @PostMapping
    public String compileCode(@RequestBody String sourceCode) {
        String encodedSourceCode = Base64.getEncoder().encodeToString(sourceCode.getBytes());

        Map<String, String> requestPayload = new HashMap<>();
        requestPayload.put("language_id", "62");  // Java
        requestPayload.put("source_code", encodedSourceCode);
        requestPayload.put("stdin", "");

        try {
            Map<String, Object> result = this.webClient.post()
                    .uri("/submissions?base64_encoded=true&wait=true")
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(requestPayload)
                    .retrieve()
                    .bodyToMono(Map.class)
                    .block();  // Mono의 결과를 블로킹 방식으로 가져옴

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

        } catch (Exception e) {
            return "An error occurred: " + e.getMessage();
        }
    }
}