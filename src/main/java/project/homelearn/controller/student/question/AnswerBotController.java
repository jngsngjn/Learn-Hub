package project.homelearn.controller.student.question;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import project.homelearn.dto.chatgpt.ChatGPTRequestDto;
import project.homelearn.dto.chatgpt.ChatGPTResponseDto;

@RestController
@RequestMapping("/bot")
@Slf4j
@RequiredArgsConstructor
public class AnswerBotController {

    @Value("${openai.api.key}")
    private String openAikey;

    @Value("${openai.model}")
    private String openaiModel;

    @Value("${openai.api.url}")
    private String openaiUrl;


    @GetMapping("/chat")
    public ResponseEntity<?> chat(@RequestParam(name = "prompt") String prompt) {

        log.info("Received prompt: {}", prompt);

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(((request, body, execution) -> {
            request.getHeaders().add("Authorization", "Bearer " + openAikey);
            return execution.execute(request, body);
        }));

        ChatGPTRequestDto request = new ChatGPTRequestDto(openaiModel, prompt);
        ChatGPTResponseDto chatGPTResponse = restTemplate.postForObject(openaiUrl, request, ChatGPTResponseDto.class);

        //댓글 DB에 저장하게끔 하도록 생각할것

        return new ResponseEntity<>(chatGPTResponse,HttpStatus.OK);
    }
}
